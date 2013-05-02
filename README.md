![headline](http://static.yeahwh.at/plugins/VillagerNames/typo.png)

VillagerNames is a plugin that allows you to name your Villagers by default based on a wordlist with names in the `config.yml`. It comes with a predefined list of about 170 medieval names.

You can find an executable version of this plugin on [dev.bukkit.org](http://dev.bukkit.org/server-mods/villagernames/).

If you search for the custom version that includes also a name dictionary for Zombie entities, checkout [the "ZombieNames" branch](https://github.com/frdmn/VillagerNames/tree/ZombieNames).

## Features:

* Lightweight
* Simple
* Easy to configure
* [MCStats](http://mcstats.org/) metrics support
* Feature: Different names per Villager Profession
* Feature: Randomize the names of all loaded Villagers
* Feature: Rename easily the villager that you're looking at
* Feature: Alias command /vn
* new Feature: Name villagers with unknown professions (if you run MCPC+ or equivalents) by [chiting](http://dev.bukkit.org/profiles/chiting/)

## Permissions

Permission | Purpose | Default
--- | --- | ---
`villagernames.randomize` | Randomize the names of all loaded Villagers | ops
`villagernames.rename` | Rename easily the villager that you're looking at | ops

## Screenshots

![screenshot-1](http://static.yeahwh.at/plugins/VillagerNames/screen1.png)

![screenshot-2](http://static.yeahwh.at/plugins/VillagerNames/screen2.png)

## config.yml

    debug: true
    farmer:
    - 'Adelaide'
    - ...
    librarian:
    - 'Brorda'
    - ...
    priest:
    - 'Edmund'
    - ...
    blacksmith:
    - 'Ine'
    - ...
    butcher:
    - 'Peter'
    - ...
    other:
    - 'Xolf'
    - ...

## Depencies

_none_

## Version

0.5

## MCstats

![metrics](http://api.mcstats.org/signature/VillagerNames.png)
