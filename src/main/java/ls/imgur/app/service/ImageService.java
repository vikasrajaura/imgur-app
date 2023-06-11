package ls.imgur.app.service;

import ls.imgur.app.dao.IImageDao;
import ls.imgur.app.model.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service("imageService")
@Transactional(readOnly = false)
public class ImageService implements IImageService {
	
	@Autowired
	private IImageDao imageDao;

	@Override
	public Image save(Image image) {
		return imageDao.save(image);
	}

	@Override
	public List<Image> saveAll(List<Image> images) {
		return imageDao.saveAll(images);
	}

	@Override
	public void persist(Image image) {
		imageDao.save(image);
	}

	@Override
	public Image update(Image image) {
		return imageDao.save(image);
	}

	@Override
	public Collection<Image> updateAll(Collection<Image> images) {
		Set<String> imageIds = new HashSet<>();
		images.forEach(e -> imageIds.add(e.getId()));
		return imageDao.findAllById(imageIds);
	}

	@Override
	public void delete(Image image) {
		imageDao.delete(image);
	}

	@Override
	public void deleteAll(List<Image> images) {
		imageDao.deleteAll(images);
	}

	@Override
	public void deleteById(String id) {
		imageDao.deleteById(id);
	}

	@Override
	public void deleteByIds(List<String> ids) {
		ids.forEach(id -> imageDao.deleteById(id));
	}

	@Override
	public Optional<Image> findById(String id)  {
		Optional<Image> image = imageDao.findById(id);
		return image;
	}

	@Override
	public List<Image> findAll()  {
		return imageDao.findAll();
	}

	@Override
	public List<Image> findByIds(List<String> ids) {
		return imageDao.findAllById(ids);
	}

}
