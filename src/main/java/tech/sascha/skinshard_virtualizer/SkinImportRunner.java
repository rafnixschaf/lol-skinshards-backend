package tech.sascha.skinshard_virtualizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import tech.sascha.skinshard_virtualizer.service.SkinImportService;

@Component
public class SkinImportRunner implements CommandLineRunner {

    @Autowired
    SkinImportService skinImportService;

    @Override
    public void run(String ...args) throws Exception {
        skinImportService.importSkins();
    }
}
