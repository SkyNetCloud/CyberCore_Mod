package ca.skynetcloud.cybercore.util.TE.cable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import ca.skynetcloud.cybercore.api.tileentity.TileEntityNames;
import ca.skynetcloud.cybercore.util.networking.config.CyberCoreConfig;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class CableTileEntity extends TileEntity implements ITickableTileEntity {
	private BlockPos masterPos = null;

	private boolean isMaster = false;

	private int[] connections = new int[] { 0, 0, 0, 0, 0, 0 };

	private int maxTransferRate = CyberCoreConfig.MaxTranfterCap.get();

	private List<BlockPos> cables = new ArrayList<>();

	private HashMap<Integer, List<PowerConnection>> connectionsMaster = new HashMap<Integer, List<PowerConnection>>() {

		private static final long serialVersionUID = 1L;

		{
			put(0, new ArrayList<PowerConnection>());
			put(1, new ArrayList<PowerConnection>());
		}
	};

	HashMap<BlockPos, Direction> producer = new HashMap<>();

	HashMap<BlockPos, Direction> consumer = new HashMap<>();

	HashMap<BlockPos, Direction> storages = new HashMap<>();

	private boolean connectionUpdate = true;

	public CableTileEntity(TileEntityType<?> tileEntityTypeIn) {
		super(tileEntityTypeIn);
	}

	public CableTileEntity() {
		this(TileEntityNames.CABLE_TE);
	}

	public void tick() {
		if (!this.world.isRemote && this.isMaster)
			transferEnergy();
	}

	private void transferEnergy() {
		int maxNeeded = 0, maxSupplied = 0, maxStorragesNeeded = 0, maxStorragesSupplied = 0;
		int amountConsumer = 0, amountProducer = 0, amountStorages = 0;
		if (this.connectionUpdate) {
			this.producer.clear();
			this.consumer.clear();
			this.storages.clear();
			for (PowerConnection con : this.connectionsMaster.get(Integer.valueOf(0))) {
				IEnergyStorage cap = getEnergyCap(con.getConnectedPos(), con.getFacing().getOpposite());
				if (cap != null)
					if (!this.consumer.containsKey(con.getConnectedPos()))
						this.consumer.put(con.getConnectedPos(), con.getFacing().getOpposite());
			}
			for (PowerConnection con : this.connectionsMaster.get(Integer.valueOf(1))) {
				IEnergyStorage cap = getEnergyCap(con.getConnectedPos(), con.getFacing().getOpposite());
				if (cap != null)
					if (!this.producer.containsKey(con.getConnectedPos()))
						this.producer.put(con.getConnectedPos(), con.getFacing().getOpposite());
			}
			List<BlockPos> intersect_PC = (List<BlockPos>) this.producer.keySet().stream()
					.filter(x -> this.consumer.containsKey(x)).collect(Collectors.toList());
			this.producer.entrySet().removeIf(entry -> intersect_PC.contains(entry.getKey()));
			this.consumer.entrySet().removeIf(entry -> intersect_PC.contains(entry.getKey()));
			for (PowerConnection con : this.connectionsMaster.get(Integer.valueOf(0))) {
				IEnergyStorage cap = getEnergyCap(con.getConnectedPos(), con.getFacing().getOpposite());
				if (cap != null)
					if (!this.storages.containsKey(con.getConnectedPos())
							&& intersect_PC.contains(con.getConnectedPos())) {
						this.storages.put(con.getConnectedPos(), con.getFacing().getOpposite());
						System.out.println("test1");
					}
			}
			this.connectionUpdate = false;
		}
		for (Map.Entry<BlockPos, Direction> entry : this.producer.entrySet()) {
			IEnergyStorage cap = getEnergyCap(entry.getKey(), entry.getValue());
			if (cap != null) {
				maxSupplied += cap.extractEnergy(this.maxTransferRate, true);
				amountProducer++;
			}
		}
		for (Map.Entry<BlockPos, Direction> entry : this.consumer.entrySet()) {
			IEnergyStorage cap = getEnergyCap(entry.getKey(), entry.getValue());
			if (cap != null) {
				maxNeeded += cap.receiveEnergy(this.maxTransferRate, true);
				amountConsumer++;
			}
		}
		for (Map.Entry<BlockPos, Direction> entry : this.storages.entrySet()) {
			IEnergyStorage cap = getEnergyCap(entry.getKey(), entry.getValue());
			if (cap != null) {
				maxStorragesSupplied += cap.extractEnergy(this.maxTransferRate, true);
				maxStorragesNeeded += cap.receiveEnergy(this.maxTransferRate, true);
				amountStorages++;
			}
		}
		if (maxNeeded + maxStorragesNeeded > 0 && maxSupplied + maxStorragesSupplied > 0) {
			int perMachineConsumer = 0, perMachineProducer = 0, perMachineStoragesIn = 0, perMachineStoragesOut = 0;
			int leftoverConsumer = 0, leftoverProducer = 0, leftoverStorageIn = 0, leftoverStorageOut = 0;
			if (maxNeeded > maxSupplied) {
				int maxSuppliedBoth = maxSupplied + maxStorragesSupplied;
				if (maxNeeded > maxSuppliedBoth) {
					perMachineProducer = this.maxTransferRate;
					perMachineStoragesOut = this.maxTransferRate;
					perMachineConsumer = maxSuppliedBoth / amountConsumer;
					leftoverConsumer = maxSuppliedBoth - perMachineConsumer * amountConsumer;
				} else {
					perMachineProducer = this.maxTransferRate;
					perMachineStoragesOut = (maxNeeded - maxSupplied) / amountStorages;
					leftoverStorageOut = maxNeeded - maxSupplied - perMachineStoragesOut * amountStorages;
					perMachineConsumer = this.maxTransferRate;
				}
			} else if (maxNeeded < maxSupplied) {
				int maxNeededBoth = maxNeeded + maxStorragesNeeded;
				if (maxNeededBoth < maxSupplied) {
					perMachineProducer = maxNeededBoth / amountProducer;
					leftoverProducer = maxNeededBoth - perMachineProducer * amountProducer;
					perMachineStoragesIn = this.maxTransferRate;
					perMachineConsumer = this.maxTransferRate;
				} else {
					perMachineProducer = this.maxTransferRate;
					perMachineStoragesIn = (maxSupplied - maxNeeded) / amountStorages;
					leftoverStorageIn = maxSupplied - maxNeeded - perMachineStoragesIn * amountStorages;
					perMachineConsumer = this.maxTransferRate;
				}
			} else {
				perMachineProducer = this.maxTransferRate;
				perMachineConsumer = this.maxTransferRate;
			}
			for (Map.Entry<BlockPos, Direction> entry : this.producer.entrySet()) {
				IEnergyStorage cap = getEnergyCap(entry.getKey(), entry.getValue());
				if (cap != null) {
					if (leftoverProducer > 0) {
						cap.extractEnergy(perMachineProducer + 1, false);
						leftoverProducer--;
						continue;
					}
					cap.extractEnergy(perMachineProducer, false);
				}
			}
			for (Map.Entry<BlockPos, Direction> entry : this.consumer.entrySet()) {
				IEnergyStorage cap = getEnergyCap(entry.getKey(), entry.getValue());
				if (cap != null) {
					if (leftoverConsumer > 0) {
						cap.receiveEnergy(perMachineConsumer + 1, false);
						leftoverConsumer--;
						continue;
					}
					cap.receiveEnergy(perMachineConsumer, false);
				}
			}
			for (Map.Entry<BlockPos, Direction> entry : this.storages.entrySet()) {
				IEnergyStorage cap = getEnergyCap(entry.getKey(), entry.getValue());
				if (cap != null) {
					if (perMachineStoragesIn > 0)
						if (leftoverStorageIn > 0) {
							cap.receiveEnergy(perMachineStoragesIn + 1, false);
							leftoverStorageIn--;
						} else {
							cap.receiveEnergy(perMachineStoragesIn, false);
						}
					if (perMachineStoragesOut > 0) {
						if (leftoverStorageOut > 0) {
							cap.extractEnergy(perMachineStoragesOut + 1, false);
							leftoverStorageOut--;
							continue;
						}
						cap.extractEnergy(perMachineStoragesOut, false);
					}
				}
			}
		}
	}

	public BlockPos getMasterPos() {
		return this.masterPos;
	}

	public void setMasterPos(BlockPos pos) {
		this.masterPos = pos;
	}

	public int getConnection(Direction facing) {
		return this.connections[facing.getIndex()];
	}

	public void setConnection(Direction facing, int i) {
		if (0 <= i && i < 5) {
			CableTileEntity te = getTECable(this.masterPos);
			if (te != null)
				te.changeConnectionMaster(this.pos, facing, this.connections[facing.getIndex()], i);
			this.connections[facing.getIndex()] = i;
		}
	}

	public void changeConnectionMaster(BlockPos pos, Direction facing, int before, int after) {
		if (before > 1) {
			this.connectionsMaster.get(before - 2).removeIf(x -> x.areEqual(pos, facing));
		}
		if (after > 1) {
			if (!this.connectionsMaster.get(after - 2).stream().anyMatch(x -> x.areEqual(pos, facing))) {
				this.connectionsMaster.get(after - 2).add(new PowerConnection(pos, facing));
			}
		}
		this.connectionUpdate = true;
	}

	private void removeValues() {
		this.masterPos = null;
		if (this.isMaster)
			removeMaster((BlockPos) null);
	}

	public void setAsMaster() {
		this.isMaster = true;
		this.masterPos = this.pos;
		this.cables.add(this.pos);
		this.connectionUpdate = true;
	}

	public void removeMaster(BlockPos newMaster) {
		this.isMaster = false;
		this.masterPos = newMaster;
		this.cables.clear();
		for (int i = 0; i < this.connectionsMaster.size(); i++)
			((List) this.connectionsMaster.get(Integer.valueOf(i))).clear();
	}

	public void addCable(BlockPos pos) {
		this.cables.add(pos);
	}

	public void removeCable(BlockPos pos) {
		this.cables.remove(pos);
	}

	public void initCable(BlockState state) {
		if (world != null) {
			TileEntity te;
			List<BlockPos> neighborMaster = new ArrayList<BlockPos>();
			for (Direction facing : Direction.values()) {
				te = this.getWorld().getTileEntity(this.getPos().offset(facing));
				if (te != null) {
					if (te instanceof CableTileEntity) {
						CableTileEntity cable = (CableTileEntity) te;
						if (!neighborMaster.contains(cable.getMasterPos())) {
							neighborMaster.add(cable.getMasterPos());
						}

					}

				}
			}

			switch (neighborMaster.size()) {
			case 0:
				this.setAsMaster();
				this.masterPos = this.pos;
				break;
			case 1:
				te = this.getWorld().getTileEntity(neighborMaster.get(0));
				if (te != null) {
					if (te instanceof CableTileEntity) {
						((CableTileEntity) te).addCable(this.getPos());
						this.setMasterPos(te.getPos());
					}
				}
				break;
			default:
				this.combineAndAdd(neighborMaster);
				break;
			}
			checkConnections();
			markDirty();
		}

	}

	public void deleteCable() {
		TileEntity te;
		List<BlockPos> neighborCables = new ArrayList<BlockPos>();
		for (Direction facing : Direction.values()) {
			te = this.getWorld().getTileEntity(this.getPos().offset(facing));
			if (te != null) {
				if (te instanceof CableTileEntity) {
					neighborCables.add(this.pos.offset(facing));
				}
			}
		}

		if (this.isMaster) {
			if (neighborCables.size() == 1) {
				CableTileEntity newMaster = (CableTileEntity) this.world.getTileEntity(neighborCables.get(0));
				this.transferMastery(newMaster, this);
				newMaster.removeCable(this.pos);

			} else if (neighborCables.size() > 1) {
				recreateNetworks(neighborCables, this.cables);
			}
		} else {
			if (neighborCables.size() == 1) {
				((CableTileEntity) this.world.getTileEntity(neighborCables.get(0))).removeCable(this.pos);
			} else if (neighborCables.size() > 1) {
				recreateNetworks(neighborCables, getTECable(this.masterPos).getCableList());
			}
		}
	}

	public void recreateNetworks(List<BlockPos> neighborcables, List<BlockPos> cables) {
		List<BlockPos> cableCopy = (List<BlockPos>) cables.stream().collect(Collectors.toList());
		for (BlockPos cablePos : cableCopy) {
			CableTileEntity te = getTECable(cablePos);
			if (te != null)
				te.removeValues();
		}
		for (BlockPos neighborcable : neighborcables) {
			CableTileEntity te = getTECable(neighborcable);
			if (te.getMasterPos() == null) {
				te.setAsMaster();
				te.sendConnections();
				te.spreadPos(this.pos);
			}
		}
	}

	public void spreadPos(BlockPos exeption) {
		for (Direction facing : Direction.values()) {
			BlockPos nextPos = this.pos.offset(facing);
			if (!nextPos.equals(exeption)) {
				CableTileEntity te = getTECable(nextPos);
				if (te != null)
					if (te.getMasterPos() == null) {
						te.setMasterPos(this.masterPos);
						getTECable(te.getMasterPos()).addCable(te.getPos());
						te.sendConnections();
						te.spreadPos(exeption);
					}
			}
		}
	}

	private void sendConnections() {
		CableTileEntity te = getTECable(this.masterPos);
		if (te != null)
			for (Direction facing : Direction.values()) {
				if (this.connections[facing.getIndex()] > 1)
					te.changeConnectionMaster(this.pos, facing, 0, this.connections[facing.getIndex()]);
			}
	}

	public void combineAndAdd(List<BlockPos> list) {
		TileEntity te = this.world.getTileEntity(list.get(0));
		list.remove(0);
		if (te != null)
			if (te instanceof CableTileEntity) {
				CableTileEntity newMaster = (CableTileEntity) te;
				for (BlockPos oldMasterPos : list) {
					TileEntity oldTE = this.world.getTileEntity(oldMasterPos);
					if (oldTE != null)
						if (oldTE instanceof CableTileEntity) {
							CableTileEntity oldMaster = (CableTileEntity) oldTE;
							transferMastery(newMaster, oldMaster);
						}
				}
				newMaster.addCable(getPos());
				setMasterPos(newMaster.getPos());
			}
	}

	public HashMap<Integer, List> getLists() {
		HashMap<Integer, List> lists = new HashMap<>();
		lists.put(Integer.valueOf(0), this.cables);
		lists.put(Integer.valueOf(1), this.connectionsMaster.get(Integer.valueOf(0)));
		lists.put(Integer.valueOf(2), this.connectionsMaster.get(Integer.valueOf(1)));
		return lists;
	}

	public void setLists(HashMap<Integer, List> lists) {
		this.cables.addAll(lists.get(Integer.valueOf(0)));
		((List) this.connectionsMaster.get(Integer.valueOf(0))).addAll(lists.get(Integer.valueOf(1)));
		((List) this.connectionsMaster.get(Integer.valueOf(1))).addAll(lists.get(Integer.valueOf(2)));
	}

	public List<BlockPos> getCableList() {
		return this.cables;
	}

	private void setCableMasterPos(BlockPos pos) {
		for (BlockPos cablePos : this.cables) {
			TileEntity te = this.world.getTileEntity(cablePos);
			if (te != null)
				if (te instanceof CableTileEntity)
					((CableTileEntity) te).setMasterPos(pos);
		}
	}

	private void transferMastery(CableTileEntity newMaster, CableTileEntity oldMaster) {
		oldMaster.setCableMasterPos(newMaster.getMasterPos());
		newMaster.setAsMaster();
		newMaster.setLists(oldMaster.getLists());
		oldMaster.removeMaster(newMaster.getMasterPos());
	}

	public CompoundNBT write(CompoundNBT compound) {
		compound.putBoolean("ismaster", this.isMaster);
		if (this.masterPos != null) {
			compound.putInt("masterposx", this.masterPos.getX());
			compound.putInt("masterposy", this.masterPos.getY());
			compound.putInt("masterposz", this.masterPos.getZ());
		}
		if (!this.cables.isEmpty()) {
			ListNBT cableList = new ListNBT();
			this.cables.stream().forEach(x -> cableList.add(NBTUtil.writeBlockPos(x)));
			compound.put("cables", (INBT) cableList);
		}
		if (this.connections.length == 6)
			compound.putIntArray("connections", this.connections);
		for (int i = 0; i < 2; i++) {
			if (((List) this.connectionsMaster.get(Integer.valueOf(i))).size() > 0) {
				List<PowerConnection> connections = this.connectionsMaster.get(Integer.valueOf(i));
				ListNBT connectionlist = new ListNBT();
				for (PowerConnection con : connections)
					connectionlist.add(con.serializeConnection());
				compound.put("connections_" + i, (INBT) connectionlist);
			}
		}
		return super.write(compound);
	}

	public void read(CompoundNBT compound) {
		super.read(compound);
		if (compound.contains("ismaster"))
			this.isMaster = compound.getBoolean("ismaster");
		if (compound.contains("masterposx"))
			this.masterPos = new BlockPos(compound.getInt("masterposx"), compound.getInt("masterposy"),
					compound.getInt("masterposz"));
		ListNBT cableList = compound.getList("cables", 10);
		if (cableList.size() > 0) {
			this.cables.clear();
			for (int j = 0; j < cableList.size(); j++)
				this.cables.add(NBTUtil.readBlockPos(cableList.getCompound(j)));
		}
		if (compound.contains("connections"))
			this.connections = compound.getIntArray("connections");
		for (int i = 0; i < 2; i++) {
			if (compound.contains("connections_" + i)) {
				ListNBT list = compound.getList("connections_" + i, 10);
				if (list.size() > 0) {
					((List) this.connectionsMaster.get(Integer.valueOf(i))).clear();
					for (int k = 0; k < list.size(); k++)
						((List<PowerConnection>) this.connectionsMaster.get(Integer.valueOf(i)))
								.add((new PowerConnection()).deserializeConnection(list.getCompound(k)));
				}
			}
		}
	}

	private IEnergyStorage getEnergyCap(BlockPos pos, Direction facing) {
		TileEntity te = this.world.getTileEntity(pos);
		if (te != null)
			if (te.getCapability(CapabilityEnergy.ENERGY, facing).isPresent())
				return (IEnergyStorage) te.getCapability(CapabilityEnergy.ENERGY, facing).orElse(null);
		return null;
	}

	private CableTileEntity getTECable(BlockPos pos) {
		if (this.world != null)
			if (!this.world.isRemote) {
				TileEntity te = this.world.getTileEntity(pos);
				if (te != null)
					if (te instanceof CableTileEntity)
						return (CableTileEntity) te;
			}
		return null;
	}

	public void checkConnections() {
		boolean shouldSendChanges = false;
		for (Direction facing : Direction.values()) {
			TileEntity te = this.world.getTileEntity(this.pos.offset(facing));
			if (te != null) {
				if (te instanceof CableTileEntity)
					if (getConnection(facing) != 1) {
						setConnection(facing, 1);
						shouldSendChanges = true;
					}
				if (te.getCapability(CapabilityEnergy.ENERGY, facing).isPresent())
					if (getConnection(facing) < 2) {
						setConnection(facing, 2);
						shouldSendChanges = true;
					}
			} else if (getConnection(facing) != 0) {
				setConnection(facing, 0);
				shouldSendChanges = true;
			}
		}
		if (shouldSendChanges && !this.world.isRemote)
			sendUpdates();
	}

	public SUpdateTileEntityPacket func_189518_D_() {
		return new SUpdateTileEntityPacket(this.pos, 3, getUpdateTag());
	}

	public CompoundNBT getUpdateTag() {
		return write(new CompoundNBT());
	}

	public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
		super.onDataPacket(net, pkt);
		handleUpdateTag(pkt.getNbtCompound());
	}

	private void sendUpdates() {
		this.world.markAndNotifyBlock(this.pos, this.world.getChunkAt(this.pos), this.world.getBlockState(this.pos),
				this.world.getBlockState(this.pos), 0);
	}

	public void rotateConnection(Direction dir) {
		rotateConnection(dir.getIndex());
	}

	public void rotateConnection(int i) {
		int next = this.connections[i] + 1;
		if (next > 3)
			next = 2;
		setConnection(Direction.byIndex(i), next);
	}
}
