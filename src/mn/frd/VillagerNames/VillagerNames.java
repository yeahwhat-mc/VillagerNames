package mn.frd.VillagerNames;

import java.io.File;
import java.util.List;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Villager.Profession;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.plugin.java.JavaPlugin;


public class VillagerNames extends JavaPlugin implements Listener {

	@Override
	public void onEnable() {
		// Define the config.yml
		final File check = new File(this.getDataFolder(), "config.yml");
		// Check for existing config.yml; if not -> create it
		if (!check.exists()) {
			this.saveDefaultConfig();
			this.reloadConfig();
		}

		// Register events
		this.getServer().getPluginManager().registerEvents(this, this);
		// Log loading
		this.getLogger().info("Loaded " + this.getDescription().getName() + " v" + this.getDescription().getVersion());
	}

	@Override
	public void onDisable() {
		// Log disabling
		this.getLogger().info("Disabled " + this.getDescription().getName() + " v" + this.getDescription().getVersion());
	}


	@EventHandler
	public void onCreatureSpawn(CreatureSpawnEvent event)
	{
		// Check if Creature is a VILLAGER
		if(event.getEntityType() == EntityType.VILLAGER)
		{
			// Check if event is instance of Villager
			if(event.getEntity() instanceof org.bukkit.entity.Villager){
				// Store the current profession in variable
				Profession currentProfession = ((Villager) event.getEntity()).getProfession();
				
				this.getLogger().info("VN: Profession - " + currentProfession.getId());
				
				// If profession == FARMER
				if (currentProfession.getId() == 0){
					// Load the FARMER names out of config.yml into a List
					List<?> names = this.getConfig().getList("farmer");
					// Shuffle that list
					java.util.Collections.shuffle(names);
					// Get the first element of the list
					String randomname = names.get(0).toString();

					// Set the custom name of the Villager
					event.getEntity().setCustomName(randomname);
					event.getEntity().setCustomNameVisible(true);
				}
				
				// If profession == LIBRARIAN
				if (currentProfession.getId() == 1){
					// Load the LIBRARIAN names out of config.yml into a List
					List<?> names = this.getConfig().getList("librarian");
					// Shuffle that list
					java.util.Collections.shuffle(names);
					// Get the first element of the list
					String randomname = names.get(0).toString();

					// Set the custom name of the Villager
					event.getEntity().setCustomName(randomname);
					event.getEntity().setCustomNameVisible(true);
				}
				
				// If profession == PRIEST
				if (currentProfession.getId() == 2){
					// Load the PRIEST names out of config.yml into a List
					List<?> names = this.getConfig().getList("priest");
					// Shuffle that list
					java.util.Collections.shuffle(names);
					// Get the first element of the list
					String randomname = names.get(0).toString();

					// Set the custom name of the Villager
					event.getEntity().setCustomName(randomname);
					event.getEntity().setCustomNameVisible(true);
				}
				
				// If profession == BLACKSMITH
				if (currentProfession.getId() == 3){
					// Load the BLACKSMITH names out of config.yml into a List
					List<?> names = this.getConfig().getList("blacksmith");
					// Shuffle that list
					java.util.Collections.shuffle(names);
					// Get the first element of the list
					String randomname = names.get(0).toString();

					// Set the custom name of the Villager
					event.getEntity().setCustomName(randomname);
					event.getEntity().setCustomNameVisible(true);
				}
				
				// If profession == BUTCHER
				if (currentProfession.getId() == 4){
					// Load the BUTCHER names out of config.yml into a List
					List<?> names = this.getConfig().getList("butcher");
					// Shuffle that list
					java.util.Collections.shuffle(names);
					// Get the first element of the list
					String randomname = names.get(0).toString();

					// Set the custom name of the Villager
					event.getEntity().setCustomName(randomname);
					event.getEntity().setCustomNameVisible(true);
				}
			}
		}
	}
}