package ca.skynetcloud.cybercore.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

import com.mojang.datafixers.util.Pair;

import ca.skynetcloud.cybercore.init.BlockInit;
import net.minecraft.advancements.criterion.EnchantmentPredicate;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.advancements.criterion.MinMaxBounds;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.LootTableProvider;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.loot.AlternativesLootEntry;
import net.minecraft.loot.ConstantRange;
import net.minecraft.loot.ItemLootEntry;
import net.minecraft.loot.LootParameterSet;
import net.minecraft.loot.LootParameterSets;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTableManager;
import net.minecraft.loot.StandaloneLootEntry;
import net.minecraft.loot.ValidationTracker;
import net.minecraft.loot.conditions.MatchTool;
import net.minecraft.loot.conditions.SurvivesExplosion;
import net.minecraft.loot.functions.ApplyBonus;
import net.minecraft.loot.functions.ExplosionDecay;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;

public class LootTables extends LootTableProvider {
	private final List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootParameterSet>> tables = new ArrayList<>();

	public LootTables(DataGenerator dataGeneratorIn) {
		super(dataGeneratorIn);
	}

	@Override
	protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootParameterSet>> getTables() {
		tables.clear();

		standardDropTable(BlockInit.CABLE);
		standardDropTable(BlockInit.Fence_Block);
		standardDropTable(BlockInit.Fence_Block_Top);
		standardDropTable(BlockInit.Fence_Gate_Block);

		return tables;
	}

	void silkBlockTable(Block b) {
		LootPool.Builder pool = LootPool.lootPool();
		pool.setRolls(ConstantRange.exactly(1));

		StandaloneLootEntry.Builder<?> silk = ItemLootEntry.lootTableItem(b)
				.when(MatchTool.toolMatches(ItemPredicate.Builder.item().hasEnchantment(
						new EnchantmentPredicate(Enchantments.SILK_TOUCH, MinMaxBounds.IntBound.atLeast(1)))));

		pool.add(AlternativesLootEntry.alternatives(silk));

		blockTable(b, LootTable.lootTable().withPool(pool));
	}

	void silkFortuneBlockTable(Block b, IItemProvider item) {
		LootPool.Builder pool = LootPool.lootPool();
		pool.setRolls(ConstantRange.exactly(1));

		StandaloneLootEntry.Builder<?> silk = ItemLootEntry.lootTableItem(b)
				.when(MatchTool.toolMatches(ItemPredicate.Builder.item().hasEnchantment(
						new EnchantmentPredicate(Enchantments.SILK_TOUCH, MinMaxBounds.IntBound.atLeast(1)))));
		StandaloneLootEntry.Builder<?> fortune = ItemLootEntry.lootTableItem(item)
				.apply(ExplosionDecay.explosionDecay()).apply(ApplyBonus.addOreBonusCount(Enchantments.BLOCK_FORTUNE));

		pool.add(AlternativesLootEntry.alternatives(silk, fortune));

		blockTable(b, LootTable.lootTable().withPool(pool));
	}

	void standardDropTable(Block b) {
		blockTable(b, LootTable.lootTable().withPool(createStandardDrops(b)));
	}

	void blockTable(Block b, LootTable.Builder lootTable) {
		addTable(b.getLootTable(), lootTable, LootParameterSets.BLOCK);
	}

	void addTable(ResourceLocation path, LootTable.Builder lootTable, LootParameterSet paramSet) {
		tables.add(Pair.of(() -> (lootBuilder) -> lootBuilder.accept(path, lootTable), paramSet));
	}

	LootPool.Builder createStandardDrops(IItemProvider itemProvider) {
		return LootPool.lootPool().setRolls(ConstantRange.exactly(1)).when(SurvivesExplosion.survivesExplosion())
				.add(ItemLootEntry.lootTableItem(itemProvider));
	}

	@Override
	protected void validate(Map<ResourceLocation, LootTable> map, ValidationTracker validationtracker) {
		map.forEach(
				(p_218436_2_, p_218436_3_) -> LootTableManager.validate(validationtracker, p_218436_2_, p_218436_3_));
	}
}
