package net.thechunk.playpen;

import net.thechunk.playpen.p3.PackageManager;
import net.thechunk.playpen.p3.provision.ExpandStep;
import net.thechunk.playpen.p3.provision.StringTemplateStep;
import net.thechunk.playpen.p3.resolver.InMemoryCacheResolver;
import net.thechunk.playpen.p3.resolver.LocalRepositoryResolver;

import java.nio.file.Paths;

public class Initialization {

    public static void packageManager(PackageManager pm) {
        // In-memory cache
        pm.addPackageResolver(new InMemoryCacheResolver());

        // Main package repository
        pm.addPackageResolver(new LocalRepositoryResolver(Paths.get(Bootstrap.getHomeDir().getPath(), "packages").toFile()));

        // Package cache
        pm.addPackageResolver(new LocalRepositoryResolver(Paths.get(Bootstrap.getHomeDir().getPath(), "cache", "packages").toFile()));

        pm.addProvisioningStep(new ExpandStep());
        pm.addProvisioningStep(new StringTemplateStep());
    }

    private Initialization() {}
}
