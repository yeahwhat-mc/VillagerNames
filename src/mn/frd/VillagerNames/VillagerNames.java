package mn.frd.VillagerNames;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Villager.Profession;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;
import org.mcstats.MetricsLite;


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
		
		// Try metrics
		try {
			MetricsLite metrics = new MetricsLite(this);
			metrics.start();
		} catch (IOException e) {
			// Stats no worky :(
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
			return true;
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
				sender.sendMessage("/villagernames rename <name>" + ChatColor.GRAY + " - " + ChatColor.GOLD + "Rename the villager that you are looking at.");
				sender.sendMessage("/villagernames randomize" + ChatColor.GRAY + " - " + ChatColor.GOLD + "Apply random names on each loaded Villager.");
				sender.sendMessage("/villagernames version" + ChatColor.GRAY + " - " + ChatColor.GOLD + "Show plugin version.");
				return true;
			} else {
				// If argument is "randomize"
				if(args[0].toString().equalsIgnoreCase("randomize")) {
					// Check if only one argument
					if(args.length == 1) {
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
							p.sendMessage(prefix + " Custom names applied to " +  Integer.toString(allEntitiesNew.size()) + " Villagers");
							return true;
						} else {
							// Send error message to player
							p.sendMessage(prefix + ChatColor.GOLD + " Missing permission: 'villagernames.randomize'");
							return true;
						}
					}
					// If argument is "version"
				} else if(args[0].toString().equalsIgnoreCase("version")){
					// Check if only one argument
					if(args.length == 1) {
						// Send version informations
						p.sendMessage(prefix + ChatColor.GOLD +" VillagerNames v" + this.getDescription().getVersion() + " by frdmn (http://frd.mn)");
						return true;
					}
					// If argument is "rename"
				} else if(args[0].toString().equalsIgnoreCase("rename")){
					// Check if player has permissions to use that command
					if(p.hasPermission("villagernames.randomize")){
						// Check if more than one argument
						if(args.length > 1) {
							// Check if target is a Villager
							if((LivingEntity) getTarget(p)==null){
								p.sendMessage(prefix + ChatColor.GOLD +" You might have missed the Villager. Try again!");
								return true;
							}

							// Define the target Villager
							LivingEntity targetVillager = (LivingEntity) getTarget(p);
							// Set the new CustomName
							targetVillager.setCustomName(com.google.common.base.Joiner.on(" ").join(Arrays.copyOfRange(args, 1, args.length)));
							targetVillager.setCustomNameVisible(true);

							// Log the actions
							this.getLogger().info( p.getDisplayName() +" renamed a Villager to: '" + com.google.common.base.Joiner.on(" ").join(Arrays.copyOfRange(args, 1, args.length)) + "' at location: '" + p.getLocation().getWorld().toString() + "," + p.getLocation().getX() + "," + p.getLocation().getY() + "," + p.getLocation().getZ() +"'");
							return true;
						} else {
							p.sendMessage(prefix + ChatColor.GOLD +" You need to enter at least one argument.");
							return true;
						}
					} else {
						// Send error message to player
						p.sendMessage(prefix + ChatColor.GOLD + " Missing permission: 'villagernames.rename'");
						return true;
					}
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

				// Initialize variable
				String professionConfig = null;
				
				// If profession == FARMER
				if (currentProfession.getId() == 0){
					professionConfig = "farmer";
				// If profession == LIBRARIAN
				} else if (currentProfession.getId() == 1){
					professionConfig = "librarian";
				// If profession == PRIEST
				} else if (currentProfession.getId() == 2){
					professionConfig = "priest";
				// If profession == BLACKSMITH
				} else if (currentProfession.getId() == 3){
					professionConfig = "blacksmith";
				// If profession == BUTCHER
				} else if (currentProfession.getId() == 4){
					professionConfig = "butcher";
				}
				
				// Load the FARMER names out of config.yml into a List
				List<?> names = this.getConfig().getList(professionConfig);
				// Shuffle that list
				java.util.Collections.shuffle(names);
				// Get the first element of the list
				String randomname = names.get(0).toString();

				// Set the custom name of the Villager
				event.getEntity().setCustomName(randomname);
				event.getEntity().setCustomNameVisible(true);
				
				// Check if log mode is enabled
				if (this.getConfig().getBoolean("log")){
					// Log the actions
					this.getLogger().info("Villager '"+ randomname +"' spawned at location: '" + event.getLocation().getWorld().toString() + "," + event.getLocation().getX() + "," + event.getLocation().getY() + "," + event.getLocation().getZ() +"'");
				}
			}
		}
	}

	// Function to get the target of players crosshair (thanks to Bukkit forums user Lemoncakecake)
	public static Entity getTarget(final Player player) {
		assert player != null;
		LivingEntity target = null;
		double targetDistanceSquared = 0;
		final double radiusSquared = 1;
		final Vector l = player.getEyeLocation().toVector(), n = player.getLocation().getDirection().normalize();
		final double cos45 = Math.cos(Math.PI / 4);
		for (final Villager other : player.getWorld().getEntitiesByClass(Villager.class)) {
			if (target == null || targetDistanceSquared > other.getLocation().distanceSquared(player.getLocation())) {
				final Vector t = other.getLocation().add(0, 1, 0).toVector().subtract(l);
				if (n.clone().crossProduct(t).lengthSquared() < radiusSquared && t.normalize().dot(n) >= cos45) {
					target = other;
					targetDistanceSquared = target.getLocation().distanceSquared(player.getLocation());
				}
			}
		}
		return target;
	}
}