package cc.ranmc.hopper.utils;

import cc.ranmc.hopper.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Shelf;
import org.bukkit.block.data.Directional;
import org.bukkit.inventory.ItemStack;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class FilterUtil {
    private static final Map<Location, Set<Material>> filterMap = new ConcurrentHashMap<>();

    public static Set<Material> getFilterItems(Block hopper) {
        if (!Main.getInstance().getConfig().getBoolean("hopper-filter", false)) return new HashSet<>();
        Location location = hopper.getLocation();
        if (filterMap.containsKey(location)) return filterMap.get(location);
        return update(hopper);
    }

    public static void check(Block block) {
        if (!Main.getInstance().getConfig().getBoolean("hopper-filter", false)) return;
        if (block == null || !(block.getType().toString().endsWith("_SHELF"))) return;
        if (!(block.getState().getBlockData() instanceof Directional directional)) return;
        Block attachedBlock = block.getRelative(directional.getFacing().getOppositeFace());
        if (attachedBlock.getType() != Material.HOPPER) return;
        Bukkit.getScheduler().runTaskLater(Main.getInstance(), ()-> FilterUtil.update(attachedBlock), 2);
    }

    public static Set<Material> update(Block hopper) {
        Location location = hopper.getLocation();
        Set<Material> list = new HashSet<>();
        list.addAll(getShelfMaterialList(location.clone().add(1, 0, 0).getBlock(), hopper));
        list.addAll(getShelfMaterialList(location.clone().add(-1, 0, 0).getBlock(), hopper));
        list.addAll(getShelfMaterialList(location.clone().add(0, 0, 1).getBlock(), hopper));
        list.addAll(getShelfMaterialList(location.clone().add(0, 0, -1).getBlock(), hopper));
        filterMap.put(location, list);
        return list;
    }

    private static Set<Material> getShelfMaterialList(Block block, Block hopper) {
        Set<Material> list = new HashSet<>();
        if (!block.getType().toString().endsWith("_SHELF")) return list;
        if (!(block.getState().getBlockData() instanceof Directional directional)) return list;
        Block attachedBlock = block.getRelative(directional.getFacing().getOppositeFace());
        if (!attachedBlock.equals(hopper)) return list;
        if (!(block.getState() instanceof Shelf shelf)) return list;
        for (ItemStack item : shelf.getInventory().getContents()) {
            if (item == null || item.getType() == Material.AIR) continue;
            list.add(item.getType());
        }
        return list;
    }

}
