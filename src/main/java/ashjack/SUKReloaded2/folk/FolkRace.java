package ashjack.SUKReloaded2.folk;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import net.minecraft.util.ResourceLocation;
import ashjack.SUKReloaded2.core.SUK2Log;
import ashjack.SUKReloaded2.core.util.SUK2IO;

public class FolkRace 
{
	public String raceName = "";
	public String raceDesc = "";
	public int raceLifespan = 100;
	public int raceMaturity = 18;
	
	public List<String> maleNameSet = new ArrayList<String>();
	public List<String> femaleNameSet = new ArrayList<String>();
	
	public List<String> surnameSet = new ArrayList<String>();
	
	public static int folkGender = 0;
	
	public int skinNumber = 0;
		
	public FolkRace(String name, String desc, int skinNo)
	{
		this.raceName = name;
		this.raceDesc = desc;
		this.skinNumber = skinNo;
	}
	
	public FolkRace(String name, String desc, int lifespan, int maturity, int skinNo, List<String> mnameset, List<String> fnameset, List<String> surnameset)
	{
		this.raceName = name;
		this.raceDesc = desc;
		this.raceLifespan = lifespan;
		this.raceMaturity = maturity;
		this.skinNumber = skinNo;
		this.maleNameSet = mnameset;
		this.femaleNameSet = fnameset;
		this.surnameSet = surnameset;
	}
	
	public FolkRace() 
	{
		
	}

	public FolkRace assignRandomRace(int gender)
	{
		folkGender = gender;
		
		newRandom();
		
		String randName = raceName;
		String randDesc = raceDesc;
		int randSpan = raceLifespan;
		int randMat = raceMaturity;
		List<String> randMaleNameSet = maleNameSet;
		List<String> randFemaleNameSet = femaleNameSet;
		List<String> randSurnameSet = surnameSet;
		
		File race = raceFiles[0];
		
		try
		{
			FileInputStream fstream = new FileInputStream(race.getAbsolutePath());
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));

			for(String line; (line = br.readLine()) != null; )
			{
				String[] commands = line.split("=");
				
				if(commands[0].contains("name") && !commands[0].contains("male"))
				{
					randName = commands[1];
				}
				if(commands[0].contains("desc"))
				{
					randDesc = commands[1];
				}
				if(commands[0].contains("lifespan"))
				{
					randSpan = Integer.parseInt(commands[1]);
				}
				if(commands[0].contains("maturity"))
				{
					randMat = Integer.parseInt(commands[1]);
				}
				if(commands[0].contains("malenameset") && !commands[0].contains("fe"))
				{
					randMaleNameSet = Arrays.asList(commands[1].split(";"));
				}
				if(commands[0].contains("femalenameset"))
				{
					randFemaleNameSet = Arrays.asList(commands[1].split(";"));
				}
				if(commands[0].contains("surnameset"))
				{
					randSurnameSet = Arrays.asList(commands[1].split(";"));
				}
			}
			
			br.close();
		}
		catch(IOException e)
		{
			
		}
		
		return new FolkRace(randName, randDesc, randSpan, randMat, rand.nextInt(raceSkinFiles.length), randMaleNameSet, randFemaleNameSet, randSurnameSet);
	}
	
	public ResourceLocation getSkinPath(int id)
	{
		String genderPath = "";
		
		if(folkGender == 0)
		{
			genderPath = "male";
		}
		else
		{
			genderPath = "female";
		}
		
		SUK2Log.log.info("Simukraft2/races/" + this.raceName.toLowerCase() + "/" + genderPath + "/" +raceSkinFiles[id].getName());
		return new ResourceLocation("simukraft2", "races/" + this.raceName.toLowerCase() + "/" + genderPath + "/"  +raceSkinFiles[id].getName());
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	public static void newRandom()
	{
		rand = new Random();
		raceFolder = new File(SUK2IO.getSimukraftFolder("races"));
		
		
		
		racesFolders = raceFolder.listFiles(new FileFilter(){
			public boolean accept(File file) {
				return file.isDirectory();
			}
		});
		
		SUK2Log.log.info(racesFolders[0].getPath());
		
		int raceFileNumber = rand.nextInt(racesFolders.length);
		
		raceGenderFolders = racesFolders[raceFileNumber].listFiles(new FileFilter() 
		{
		    public boolean accept(File file) 
		    {
		    	return file.isDirectory();
		    }
		});
		
		SUK2Log.log.info(raceGenderFolders[0].getPath());
		
		
		
		raceFiles = racesFolders[raceFileNumber].listFiles(new FilenameFilter() 
		{
		    public boolean accept(File dir, String name) 
		    { 
		        return name.toLowerCase().endsWith(".suk2race");
		    }
		});
		
		SUK2Log.log.info(raceFiles[0].getPath());
		
		int gndr = 0;
		
		if(folkGender == 1)
		{
			gndr = 0;
		}
		else
		{
			gndr = 1;
		}
		
		raceSkinFiles = raceGenderFolders[gndr].listFiles(new FilenameFilter() 
		{
		    public boolean accept(File dir, String name) 
		    {
		        return name.toLowerCase().endsWith(".png");
		    }
		});
		
		SUK2Log.log.info(raceSkinFiles[0].getPath());
	}
	
	
	static Random rand = new Random();
	static File raceFolder = new File(SUK2IO.getSimukraftFolder("races"));
	
	
	
	static File[] racesFolders = raceFolder.listFiles(new FileFilter(){
		public boolean accept(File file) {
			return file.isDirectory();
		}
	});
	
	static File[] raceGenderFolders = racesFolders[rand.nextInt(racesFolders.length)].listFiles(new FileFilter() 
	{
	    public boolean accept(File file) 
	    {
	    	return file.isDirectory();
	    }
	});
	
	static File[] raceFiles = raceGenderFolders[rand.nextInt(raceGenderFolders.length)].listFiles(new FilenameFilter() 
	{
	    public boolean accept(File dir, String name) 
	    {
	        return name.toLowerCase().endsWith(".suk2race");
	    }
	});
	
	public static File[] raceSkinFiles = raceGenderFolders[rand.nextInt(raceGenderFolders.length-1)].listFiles(new FilenameFilter() 
	{
	    public boolean accept(File dir, String name) 
	    {
	        return name.toLowerCase().endsWith(".png");
	    }
	});
}
