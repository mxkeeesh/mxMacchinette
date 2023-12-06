package me.mxkeeesh.mxmacchinette.listeners;

import me.mxkeeesh.mxmacchinette.MxMacchinette;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import me.mxkeeesh.mxmacchinette.gui.gui;

import static me.mxkeeesh.mxmacchinette.utils.cc;

public class listeners implements Listener {


    @EventHandler
    public void onInventoryClick(InventoryClickEvent event){
        if (!event.getView().getTitle().equals("Macchinetta Snack")){
            return;
        }

        Player player = (Player) event.getWhoClicked();
        int slot = event.getSlot();
        Economy economy = MxMacchinette.getEconomy();

        ItemStack cibo1 = new ItemStack(Material.APPLE);
        ItemMeta meta = cibo1.getItemMeta();
        meta.setDisplayName(cc(MxMacchinette.getInstance().getConfig().getString("cibo-1.name")));
        meta.setCustomModelData(MxMacchinette.getInstance().getConfig().getInt("cibo-1.cmdata"));
        cibo1.setItemMeta(meta);

        ItemStack cibo2 = new ItemStack(Material.APPLE);
        ItemMeta meta2 = cibo2.getItemMeta();
        meta2.setDisplayName(cc(MxMacchinette.getInstance().getConfig().getString("cibo-2.name")));
        meta2.setCustomModelData(MxMacchinette.getInstance().getConfig().getInt("cibo-2.cmdata"));
        cibo2.setItemMeta(meta2);

        ItemStack cibo3 = new ItemStack(Material.APPLE);
        ItemMeta meta3 = cibo3.getItemMeta();
        meta3.setDisplayName(cc(MxMacchinette.getInstance().getConfig().getString("cibo-3.name")));
        meta3.setCustomModelData(MxMacchinette.getInstance().getConfig().getInt("cibo-3.cmdata"));
        cibo3.setItemMeta(meta3);

        event.setCancelled(true);


        if(slot == 11){
            if(economy.getBalance(player) >= MxMacchinette.getInstance().getConfig().getInt("cibo-1.prezzo")){
                player.getInventory().addItem(cibo1);
                economy.withdrawPlayer(player, MxMacchinette.getInstance().getConfig().getInt("cibo-1.prezzo"));
            }
        }

        else if(slot == 13){
            if(economy.getBalance(player) >= MxMacchinette.getInstance().getConfig().getInt("cibo-2.prezzo")){
                player.getInventory().addItem(cibo2);
                economy.withdrawPlayer(player, MxMacchinette.getInstance().getConfig().getInt("cibo-2.prezzo"));
            }
        }

        else if (slot == 15){
            if(economy.getBalance(player) >= MxMacchinette.getInstance().getConfig().getInt("cibo-3.prezzo")) {
                player.getInventory().addItem(cibo3);
                economy.withdrawPlayer(player, MxMacchinette.getInstance().getConfig().getInt("cibo-3.prezzo"));
            }
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onArmorStandInteract(final PlayerInteractAtEntityEvent event){
        final Player p = event.getPlayer();
        final ArmorStand armorStand = (ArmorStand)event.getRightClicked();

        if (armorStand.getEquipment().getHelmet().getItemMeta().getCustomModelData() == 16){
            Inventory inv = Bukkit.createInventory(null, 9*4, "Macchinetta Snack");


            inv.setItem(11, gui.getItem(new ItemStack(Material.APPLE), MxMacchinette.getInstance().getConfig().getString("cibo-1.name"), MxMacchinette.getInstance().getConfig().getInt("cibo-1.cmdata"), "&7Prezzo: &f€" + MxMacchinette.getInstance().getConfig().getInt("cibo-1.prezzo"), "", "&a → Clicca per acquistare"));
            inv.setItem(13, gui.getItem(new ItemStack(Material.APPLE), MxMacchinette.getInstance().getConfig().getString("cibo-2.name"), MxMacchinette.getInstance().getConfig().getInt("cibo-2.cmdata"), "&7Prezzo: &f€"+ MxMacchinette.getInstance().getConfig().getInt("cibo-2.prezzo"), "", "&a → Clicca per acquistare"));
            inv.setItem(15, gui.getItem(new ItemStack(Material.APPLE), MxMacchinette.getInstance().getConfig().getString("cibo-3.name"), MxMacchinette.getInstance().getConfig().getInt("cibo-3.cmdata"), "&7Prezzo: &f€"+ MxMacchinette.getInstance().getConfig().getInt("cibo-3.prezzo"), "", "&a → Clicca per acquistare"));




            p.openInventory(inv);
        }
    }

}
