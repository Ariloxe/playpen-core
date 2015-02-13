package net.thechunk.playpen;

import lombok.Getter;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Paths;

public class Bootstrap {
    @Getter
    private static File homeDir;

    public static void main(String[] args) {
        boolean didCopyResources = false;

        try {
            homeDir = new File(Bootstrap.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath()).getParentFile();
        }
        catch(URISyntaxException e) {}

        try {
            File f = Paths.get(homeDir.getPath(), "logging.xml").toFile();
            if(!f.exists()) {
                JarUtils.exportResource(Bootstrap.class, "/logging.xml", f.getPath());
                didCopyResources = true;
            }

            f = Paths.get(homeDir.getPath(), "keystore.json").toFile();
            if (!f.exists()) {
                JarUtils.exportResource(Bootstrap.class, "/keystore.json", f.getPath());
                didCopyResources = true;
            }

            f = Paths.get(homeDir.getPath(), "packages.json").toFile();
            if(!f.exists()) {
                JarUtils.exportResource(Bootstrap.class, "/packages.json", f.getPath());
                didCopyResources = true;
            }

            f = Paths.get(homeDir.getPath(), "local.json").toFile();
            if(!f.exists()) {
                JarUtils.exportResource(Bootstrap.class, "/local.json", f.getPath());
                didCopyResources = true;
            }

            f = Paths.get(homeDir.getPath(), "network.json").toFile();
            if(!f.exists()) {
                JarUtils.exportResource(Bootstrap.class, "/network.json", f.getPath());
                didCopyResources = true;
            }

            f = Paths.get(homeDir.getPath(), "pp.bat").toFile();
            if(!f.exists()) {
                JarUtils.exportResource(Bootstrap.class, "/pp.bat", f.getPath());
                didCopyResources = true;
            }

            f = Paths.get(homeDir.getPath(), "pp.sh").toFile();
            if(!f.exists()) {
                JarUtils.exportResource(Bootstrap.class, "/pp.sh", f.getPath());
                didCopyResources = true;
            }

            if(Paths.get(homeDir.getPath(), "cache", "packages").toFile().mkdirs())
                didCopyResources = true;

            if(Paths.get(homeDir.getPath(), "packages").toFile().mkdirs())
                didCopyResources = true;

            if(Paths.get(homeDir.getPath(), "plugins").toFile().mkdirs())
                didCopyResources = true;
        }
        catch(Exception e) {
            System.err.println("Unable to copy default resources");
            e.printStackTrace(System.err);
            return;
        }

        if(didCopyResources) {
            System.err.println("It looks like you were missing some resource files, so I've copied some defaults for you! " +
                    "I'll give you a chance to edit them. Bye!");
            return;
        }

        if(args.length < 1) {
            System.err.println("playpen <local/network/p3> [arguments...]");
            return;
        }

        switch(args[0].toLowerCase()) {
            case "local":

                break;

            case "network":
                break;

            case "p3":
                P3Tool.run(args);
                break;

            default:
                System.err.println("playpen <local/network/p3> [arguments...]");
                return;
        }
    }

}
