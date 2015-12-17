package ashjack.SUKReloaded2.entity;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.INpc;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveIndoors;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAIOpenDoor;
import net.minecraft.entity.ai.EntityAIRestrictOpenDoor;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.EntityAIWatchClosest2;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInvBasic;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import ashjack.SUKReloaded2.client.gui.GuiEntityFolk;
import ashjack.SUKReloaded2.core.SUK2Log;
import ashjack.SUKReloaded2.entity.ai.SUKAIWander;
import ashjack.SUKReloaded2.folk.FolkData;
import ashjack.SUKReloaded2.folk.inventory.MyInventory;

public class EntityFolk extends EntityCreature implements INpc, IInvBasic
{
	
	public MyInventory inventory;
	public FolkData theFolk = FolkData.getFolkDataByEntityId(this.getEntityId());
	public ResourceLocation textureLocation = null;
	
	/** used to kill Minecraft spawned EntityFolks, we'll spawn them */
	private long ghostTimer = -1;
	
	public EntityFolk(World p_i1602_1_) 
	{
		super(p_i1602_1_);
		
		this.setupInventory();
        this.inventory.setInventorySlotContents(0, new ItemStack(Items.apple));
        this.inventory.setInventorySlotContents(1, new ItemStack(Items.carrot));
		
		setSize(0.6F, 1.8F);
		this.getNavigator().setAvoidsWater(false);
		this.getNavigator().setEnterDoors(true);
		this.getNavigator().setBreakDoors(true);
		this.getNavigator().setCanSwim(true);
		this.tasks.addTask(1, new EntityAILookIdle(this));
		this.tasks.addTask(2, new EntityAIMoveIndoors(this));
		this.tasks.addTask(3, new EntityAIRestrictOpenDoor(this));
		this.tasks.addTask(10, new EntityAIWatchClosest2(this, EntityPlayer.class, 3.0F, 1.0F));
		this.tasks.addTask(9, new EntityAIWatchClosest(this, EntityLiving.class, 8.0F));
		this.tasks.addTask(9, new EntityAIOpenDoor(this, true));
		this.tasks.addTask(9, new SUKAIWander(this, 0.6D));
		this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 0.3D));
		this.tasks.addTask(4, new EntityAISwimming(this));
	}
	
	@Override
	public void onUpdate() 
	{
		super.onUpdate();
		
		if (theFolk == null) {
			if (!this.isDead) {
				if (ghostTimer == -1) {
					ghostTimer = System.currentTimeMillis();
				}
				
				theFolk = FolkData.getFolkDataByEntityId(this.getEntityId());

				if (theFolk == null
						&& (System.currentTimeMillis() - ghostTimer > 5000)) {
					SUK2Log.log
							.info("EntityFolk: "
									+ this.getEntityId()
									+ " - their data has been null for more than 5s, so killing");
					this.setDead();
				}
			}
		}
	 else {
		 theFolk.onUpdate();
		 
		/*if (theFolk.isWorking) {
			float s = (float) (Math.sin(System.currentTimeMillis() * 0.01) / 10) + 0.1f;
			swingProgress = s;
		} else {
			swingProgress = 0.0f;
		}*/
	 }
	}
		
	
	
	protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.5D);
    }
	
	public boolean isAIEnabled()
	{
		 return true;
	}
	
	//Inventory
	public int howManySlots() {
        return 8;
    }

    private void setupInventory() {
        MyInventory gear1 = this.inventory;
        this.inventory = new MyInventory("MyInventory", howManySlots());
        this.inventory.func_110133_a(this.getCommandSenderName());

        if (gear1 != null) {
            gear1.func_110132_b(this);
            int i = Math.min(gear1.getSizeInventory(), this.inventory.getSizeInventory());

            for (int j = 0; j < i; ++j) {
                ItemStack itemstack = gear1.getStackInSlot(j);

                if (itemstack != null) {
                    this.inventory.setInventorySlotContents(j, itemstack.copy());
                }
            }

            gear1 = null;
        }

        this.inventory.func_110134_a(this);
    }
    
    public void onDeath(DamageSource ds)
    {
        super.onDeath(ds);
        if (inventory != null && !this.worldObj.isRemote) {
            for (int i = 0; i < inventory.getSizeInventory(); ++i) {
                ItemStack itemstack = inventory.getStackInSlot(i);

                if (itemstack != null) {
                    this.entityDropItem(itemstack, 0.0F);
                }
            }
        }
    }

    public void writeEntityToNBT(NBTTagCompound com) {
        NBTTagList nbttaglist = new NBTTagList();
        for (int i = 0; i < this.inventory.getSizeInventory(); ++i) {
            ItemStack itemstack = this.inventory.getStackInSlot(i);
            if (itemstack != null) {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte) i);
                itemstack.writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
            com.setTag("Items", nbttaglist);
        }
    }


    public void readEntityFromNBT(NBTTagCompound com) {
        NBTTagList nbttaglist = com.getTagList("Items", 10);
        this.setupInventory();
        for (int i = 0; i < nbttaglist.tagCount(); ++i) {
            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
            int j = nbttagcompound1.getByte("Slot") & 255;
            if (j >= 0 && j < this.inventory.getSizeInventory()) {
                this.inventory.setInventorySlotContents(j, ItemStack.loadItemStackFromNBT(nbttagcompound1));
            }
        }
    }

    @Override
    public boolean interact(EntityPlayer player) {
        super.interact(player);
        SUK2Log.log.info("Interacted");
        //if(player.worldObj.isRemote)Minecraft.getMinecraft().displayGuiScreen(new MyEntityGui(player.inventory, inventory));//player.openGui(SUK2.instance, 5, this.worldObj, this.getEntityId(), 0, 0); //we will use x coord for sending entityId
        Minecraft.getMinecraft().displayGuiScreen(new GuiEntityFolk(theFolk, player));
        return true;
    }

    public void onInventoryChanged(InventoryBasic inv) {}
}
