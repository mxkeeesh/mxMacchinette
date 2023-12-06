package me.mxkeeesh.mxmacchinette;

import me.mxkeeesh.mxmacchinette.listeners.listeners;
import me.mxkeeesh.mxmacchinette.providers.FileManager;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;


import java.io.File;

import static me.mxkeeesh.mxmacchinette.utils.cc;


public final class MxMacchinette extends JavaPlugin {

    private static Economy econ = null;
    private static MxMacchinette instance;

    public static MxMacchinette getInstance() {
        return instance;
    }
    @Override
    public void onEnable() {
        instance = this;
        if (!setupEconomy() ) {
            System.out.println(cc("&8[&9Macchinette&8] &cVault non trovato! Disabilitando il plugin..."));
            return;
        }
        FileManager configuration = new FileManager(getDataFolder(), "config.yml");
        getConfig().options().copyDefaults();
        System.out.println(cc("&8[&9Macchinette&8] &aPlugin caricato con successo"));
        this.getServer().getPluginManager().registerEvents(new listeners(), this);
    }


    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }

        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);

        if (rsp == null) {
            return false;
        }

        econ = rsp.getProvider();
        return econ != null;
    }

    public static Economy getEconomy() {
        return econ;
    }
}
