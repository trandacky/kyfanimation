package com.dacky.service.implement;

import com.dacky.controller.BaseResponse;
import com.dacky.domain.Firm;
import com.dacky.repository.CategoryRepository;
import com.dacky.repository.FirmRepository;
import com.dacky.service.appservice.FirmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class FirmServiceImpl implements FirmService {

    @Autowired
    private FirmRepository firmRepository;


    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Flux<Firm> getAllFirm(int offset, int limit) {
        return firmRepository.findPaging(offset, limit);
    }

    @Override
    public Mono<Long> rowCount() {
        return firmRepository.count();
    }

    @Override
    public Mono<Firm> saveNewFirm(Firm firm) {
        return categoryRepository.findById(firm.getCategoryId()).flatMap(category -> {
            Firm firm1 = new Firm();
            firm1.setCategoryId(category.getId());
            firm1.setName(firm.getName());
            firm1.setImageUrl(firm.getImageUrl());
            firm1.setCountEpisode(firm.getCountEpisode());
            firm1.setIntroduce(firm.getIntroduce());
            firm1.setReleaseEpisode(firm.getReleaseEpisode());
            firm1.setReleaseTime(firm.getReleaseTime());
            return firmRepository.save(firm1);
        });
//        return firmRepository.save(firm);
    }

    @Override
    public Mono<Firm> updateFirm(Firm firm) {
        return categoryRepository.findById(firm.getCategoryId()).flatMap(category ->
            firmRepository.findById(firm.getId()).flatMap(firm1 -> {
                firm1.setCategoryId(category.getId());
                firm1.setName(firm.getName());
                firm1.setImageUrl(firm.getImageUrl());
                firm1.setCountEpisode(firm.getCountEpisode());
                firm1.setIntroduce(firm.getIntroduce());
                firm1.setReleaseEpisode(firm.getReleaseEpisode());
                firm1.setReleaseTime(firm.getReleaseTime());
                return firmRepository.save(firm1);
            })
        );
    }
}
