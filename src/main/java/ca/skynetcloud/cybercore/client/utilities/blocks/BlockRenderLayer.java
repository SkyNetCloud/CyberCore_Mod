package ca.skynetcloud.cybercore.client.utilities.blocks;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Retention(value= RetentionPolicy.RUNTIME)
@Target(value= ElementType.FIELD)
public @interface BlockRenderLayer {

    enum RenderLayer {
        CUTOUT, TRANSLUCENT;
    }

    RenderLayer layer() default RenderLayer.CUTOUT;
}
