package com.javawebspringboot.websitemovie.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javawebspringboot.websitemovie.model.Movie;
import com.javawebspringboot.websitemovie.model.Slide;
import com.javawebspringboot.websitemovie.repository.SlideRepository;
import com.javawebspringboot.websitemovie.service.SlideService;

@Service
@Transactional
public class SlideServiceImpl implements SlideService {

	@Autowired
	private SlideRepository slideRepository;

	@Override
	public List<Slide> findAllSlide() {
		return slideRepository.findAll();
	}

	@Override
	public void updateSlideByStatus(Integer idSlide) {

		Slide slide = slideRepository.findByIdSlide(idSlide);
		switch (slide.getStatus()) {
		case 1:
			slide.setStatus(0);
			break;

		case 0:
			slide.setStatus(1);
			break;
		}
		slideRepository.save(slide);

	}

	@Override
	public List<Slide> findAllSlideByStatus1() {
		return slideRepository.findByStatus(1);
	}

	@Override
	public void deleteSlideByIdSlide(Integer idSlide) {
		slideRepository.deleteByIdSlide(idSlide);

	}

	@Override
	public Slide findByMovie(Movie movieUpdate) {
		return slideRepository.findByMovie(movieUpdate);
	}

}
