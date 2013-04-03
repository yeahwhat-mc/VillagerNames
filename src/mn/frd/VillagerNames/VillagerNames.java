package mn.frd.VillagerNames;

import java.io.File;
import java.util.Collection;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
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

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {

		// Define prefix
		String prefix = ChatColor.RED + "[VillagerNames]" + ChatColor.RESET;

		// Check if instance is player and not console
		if (!(sender instanceof Player)){
			this.getLogger().info("No console commands");
			return false;
		} 

		// Define player object
		final Player p = (Player) sender;

		//  /villagernames command
		if (cmd.getName().equalsIgnoreCase("villagernames"))
		{
			// Check for arguments
			if(args.length == 0) {
				// Send command overview
				sender.sendMessage(prefix + ChatColor.YELLOW +" Commands:");
				sender.sendMessage("/villagernames randomize" + ChatColor.GRAY + " - " + ChatColor.GOLD + "Apply random names on each loaded Villager.");
				sender.sendMessage("/villagernames version" + ChatColor.GRAY + " - " + ChatColor.GOLD + "Show plugin version.");
				return true;
			} else if(args.length == 1) {
				// If argument is "randomize"
				if(args[0].toString().equalsIgnoreCase("randomize")) {
					// Check if player has permissions to use that command
					if(p.hasPermission("villagernames.randomize")){
						// Get current world
						World currentWorld = p.getWorld();

						// Load all loaded Villagers
						Collection<Villager> allEntitiesNew = currentWorld.getEntitiesByClass(Villager.class);

						for (Villager e : allEntitiesNew){
							// Initialize variable
							String currentProfession = null;

							// Get profession
							switch (e.getProfession().getId()){
							case 0: currentProfession = "farmer"; break;
							case 1: currentProfession = "librarian"; break;
							case 2: currentProfession = "priest"; break;
							case 3: currentProfession = "blacksmith"; break;
							case 4: currentProfession = "butcher"; break;
							}

							// Load the FARMER names out of config.yml into a List
							List<?> names = this.getConfig().getList(currentProfession);
							// Shuffle that list
							java.util.Collections.shuffle(names);
							// Get the first element of the list
							String randomname = names.get(0).toString();

							// Set the custom name of the Villager
							e.setCustomName(randomname);
							e.setCustomNameVisible(true);
						}
						
						// Log amount of changed names
						this.getLogger().info("Custom names applied to " +  Integer.toString(allEntitiesNew.size()) + " Villagers");
						return true;
					} else {
						// Send error message to player
						p.sendMessage(prefix + ChatColor.GOLD + " Missing permission: 'villagernames.randomize'");
						return true;
					}
				// If argument is "version"
				} else if(args[0].toString().equalsIgnoreCase("version")){
					// Send version informations
					p.sendMessage(prefix + ChatColor.GOLD +" VillagerNames v" + this.getDescription().getVersion() + " by frdmn (http://frd.mn)");
					return true;
				}
			}
		}
		return false;
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