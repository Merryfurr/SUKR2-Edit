package ashjack.SUKReloaded2.building;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

import net.minecraft.block.Block;
import ashjack.SUKReloaded2.core.util.SUK2IO;

public class Building 
{
	//Identity
	public String displayName;
	public String description = "None";
	public String author;
	
	public BuildingType type;
	
	public int elevationLevel;
	
	//Stats
	int[] dimensions = { 0, 0, 0 }; //The dimensions of the building
	Block[] structure; //Holds the 3d array of blocks in the structure
	int[] structureSub; //Holds the 3d array of block metadata's in the structure
	
	//Other stuffs
	
	private void loadStructure()
	{  
		FileInputStream fstream;
		try 
		{
			fstream = new FileInputStream(
					SUK2IO.getSimukraftFolder() + "/buildings/" + type.type.toString()
					+ "/" + displayName + ".txt");
			
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			
			//Dimensions
			strLine = br.readLine().toString().toLowerCase().trim();
			String[] d = strLine.split("x");
			this.dimensions[0] = Integer.parseInt(d[0]);
			this.dimensions[1] = Integer.parseInt(d[1]);
			this.dimensions[2] = Integer.parseInt(d[2]);
			
			//Structure Arrays
			structure = new Block[dimensions[0] * dimensions[1] * dimensions[2]];
			structureSub = new int[dimensions[0] * dimensions[1] * dimensions[2]];
			
			//Key - A=minecraft:air;B=minecraft:dirt; etc
			strLine = br.readLine().toString().trim(); 
			HashMap thekey = new HashMap();
			d = strLine.split(";");

			for (int i = 0; i < d.length; i++)
			{
				String[] k = d[i].split("=");
				if(!k[0].toUpperCase().contentEquals("AU") && !k[0].toUpperCase().contentEquals("DESC") && !k[0].toUpperCase().contentEquals("ELEV") && !k[0].toUpperCase().contentEquals("REQ") && !k[0].toUpperCase().contentEquals("INFREQ"))
				{
					String[] blockInfo = k[1].split(",");
					thekey.put(k[0], Block.getIdFromBlock(Block.getBlockFromName(blockInfo[0])) + ":" + blockInfo[1]); // / P minecraft:dirt

				}

				if (k[0].toUpperCase().contentEquals("AU"))   // Author of this
				{
					// building
					// stored in key
					// AU=me;
					this.author = k[1].trim();
				}
				else if (k[0].toUpperCase().contentEquals("DESC"))   // Description of this
				{
					// building
					// stored in key
					// DESC=building description;
					this.description = k[1];
				}
				else if (k[0].toUpperCase().contentEquals("ELEV"))   // Elevation constructor box should be at
				{
					// building
					// stored in key
					// ELEV=-1
					this.elevationLevel = Integer.parseInt(k[1]);
				}
				else if (k[0].toUpperCase().contentEquals("REQ"))   // Building that is required before this one can be built
				{
					// building
					// stored in key
					// REQ=farm.wheat
					this.elevationLevel = Integer.parseInt(k[1]);
				}
				else if (k[0].toUpperCase().contentEquals("INFREQ"))   // Infrastructure that the building requires to be able to function
				{
					// building
					// stored in key
					// INF=water
					//Infrastructure coming soon, sorry

					/*if(k[1].toLowerCase().contentEquals("water"))
					{
						this.infrastructureRequirement = new InfrastructureWater();
					}

					if(k[1].toLowerCase().contentEquals("electricity"))
					{
						this.infrastructureRequirement = new InfrastructureElectricity();
					}*/
				}
			}
			
			br.close();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
	}
	
	//Util Functions
	public String getDimensionString(int[] dim)
	{
		return String.valueOf(dim[0] + "x" + dim[1] + "x" + dim[2]);
	}
}
