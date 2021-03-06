package ashjack.SUKReloaded2.entity;

import ashjack.SUKReloaded2.folk.FolkData;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderFolk extends RenderLiving
{
    protected ResourceLocation folkSkin;

    public RenderFolk(ModelBase par1ModelBase, float parShadowSize)
    {
        super(par1ModelBase, parShadowSize);
    }
 
    @Override
    protected void preRenderCallback(EntityLivingBase entity, float f)
    {
    	preRenderCallbackFolk((EntityFolk) entity, f);
    }
  
    protected void preRenderCallbackFolk(EntityFolk entity, float f)
    {
        // some people do some G11 transformations or blends here, like you can do
        // GL11.glScalef(2F, 2F, 2F); to scale up the entity
        // which is used for Slime entities.  I suggest having the entity cast to
        // your custom type to make it easier to access fields from your 
        // custom entity, eg. GL11.glScalef(entity.scaleFactor, entity.scaleFactor, 
        // entity.scaleFactor); 
    }

    /**
    * Returns the location of an entity's texture. Doesn't seem to be called 
    * unless you call Render.bindEntityTexture.
    */
    @Override
    protected ResourceLocation getEntityTexture(Entity par1Entity)
    {
    	if (par1Entity instanceof EntityFolk) 
    	{
			EntityFolk theFolk = (EntityFolk)par1Entity;
			FolkData theData = FolkData.getFolkDataByEntityId(theFolk.getEntityId());
			ResourceLocation myTexture = theData.race.getSkinPath(theData.race.skinNumber);
			return myTexture;
		}
		return null;
    }
}