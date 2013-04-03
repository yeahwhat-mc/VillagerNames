package mn.frd.VillagerNames;

import java.io.File;
import java.util.List;

import org.bukkit.entity.EntityType;
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
			// Load the names out of config.yml into a List
			List<?> names = this.getConfig().getList("villager");
			// Shuffle that list
			java.util.Collections.shuffle(names);
			// Get the first element of the list
			String randomname = names.get(0).toString();

			// Set the custom name of the Villager
			event.getEntity().setCustomName(randomname);
			event.getEntity().setCustomNameVisible(true);
		}

		// Check if Creature is a ZOMBIE
		if(event.getEntityType() == EntityType.ZOMBIE)
		{
			// Load the names out of config.yml into a List
			List<?> names = this.getConfig().getList("zombies");
			// Shuffle that list
			java.util.Collections.shuffle(names);
			// Get the first element of the list
			String randomname = names.get(0).toString();

			// Set the custom name of the zombie
			event.getEntity().setCustomName(randomname);
			event.getEntity().setCustomNameVisible(true);
		}
	}
}