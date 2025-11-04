package tech.sascha.skinshard_virtualizer.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.sascha.skinshard_virtualizer.LcuClient;
import tech.sascha.skinshard_virtualizer.service.SummonerImportSkinsService;

@RestController
@RequestMapping("/summoner")
public class SummonerController {

    @Autowired
    LcuClient lcuClient;

    @Autowired
    SummonerImportSkinsService summonerImportSkinsService;

    @PostMapping("/summoner/skins")
    public ResponseEntity<Void> fetchSkins() {

        summonerImportSkinsService.importSkins();

        return ResponseEntity.ok().build();
    }

}
