package service;

import java.util.ArrayList;
import java.util.List;

import com.google.protobuf.ServiceException;

import dao.GoodDao;
import entity.Good;
import exception.DaoException;

public class GoodService {
	private GoodDao main = new GoodDao();

	
	public List<Good> loadAll() throws ServiceException {
		List<Good> goods = new ArrayList<>();
		try {
			goods = main.getAll();
		} catch (DaoException e) {
			throw new ServiceException("Проблемы с загрузкой товаров из БД " + e.getMessage(), e);
		}
		return goods;
	}

	
	public List<Good> load(String name) throws ServiceException {
		List<Good> goods = new ArrayList<>();
		try {
			goods = main.find(name);
		} catch (DaoException e) {
			throw new ServiceException("Проблемы с поиском товара в БД " + e.getMessage(), e);
		}
		return goods;
	}

	
	public void add(Good object) throws ServiceException {
		try {
			main.add(object);
		} catch (DaoException e) {
			throw new ServiceException("Проблемы с добавлением товара в БД " + e.getMessage(), e);
		}
	}

	
	public void delete(String id) throws ServiceException {
		try {
			main.delete(id);
		} catch (DaoException e) {
			throw new ServiceException("Проблемы с удалением товара в БД " + e.getMessage(), e);
		}
	}

	
	public void update(String id, String nameNew, String priceNew) throws ServiceException {
		try {
			main.update(id, nameNew, priceNew);
		} catch (DaoException e) {
			throw new ServiceException("Проблемы с изменением товара в БД " + e.getMessage(), e);
		}
	}

}
