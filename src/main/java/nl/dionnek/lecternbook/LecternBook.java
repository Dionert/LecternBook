package nl.dionnek.lecternbook;

import nl.dionnek.lecternbook.commands.LecternBookCommand;
import nl.dionnek.lecternbook.listener.LecternClickListener;
import nl.dionnek.lecternbook.manager.LecternBookManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class LecternBook extends JavaPlugin {

    private LecternBookManager lecternBookManager;
    private static LecternBook instance;

    @Override
    public void onEnable() {
        lecternBookManager = new LecternBookManager();
        instance = this;

        getCommand("page").setExecutor(new LecternBookCommand());
        getServer().getPluginManager().registerEvents(new LecternClickListener(), this);

    }

    @Override
    public void onDisable() {
    }


    public LecternBookManager getLecternBookManager() {
        return lecternBookManager;
    }

    public static LecternBook inst() {
        return instance;
    }

}
