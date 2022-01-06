package service;

import java.util.ArrayList;
import java.util.List;

import com.google.protobuf.ServiceException;

import dao.GoodDao;
import entity.Good;
import exception.DaoException;

public class GoodService {
	private GoodDao main;

	/**
	 * Метод загрузки всех товаров и базы данных
	 */
	public List<Good> loadAll() throws ServiceException {
		List<Good> goods = new ArrayList<>();
		try {
			goods = main.getAll();
		} catch (DaoException e) {
			throw new ServiceException("Проблемы с загрузкой товаров из БД " + e.getMessage(), e);
		}
		return goods;
	}

	/**
	 * Метод загрузки товаров,найденных по названию
	 */
	public List<Good> loadFindProductByName(String name) throws ServiceException {
		List<Good> goods = new ArrayList<>();
		try {
			goods = main.findByName(name);
		} catch (DaoException e) {
			throw new ServiceException("Проблемы с поиском товара в БД " + e.getMessage(), e);
		}
		return goods;
	}

	/**
	 * Метод добавления нового товара в базу данных
	 */
	public void add(Good object) throws ServiceException {
		try {
			main.add(object);
		} catch (DaoException e) {
			throw new ServiceException("Проблемы с добавлением товара в БД " + e.getMessage(), e);
		}
	}

	/**
	 * Метод удаления товара из базы данных
	 */
	public void delete(String id) throws ServiceException {
		try {
			main.delete(id);
		} catch (DaoException e) {
			throw new ServiceException("Проблемы с удалением товара в БД " + e.getMessage(), e);
		}
	}

	/**
	 * Метод изменения товара в базе данных
	 */
	public void update(String id, String nameNew, String priceNew) throws ServiceException {
		try {
			main.update(id, nameNew, priceNew);
		} catch (DaoException e) {
			throw new ServiceException("Проблемы с изменением товара в БД " + e.getMessage(), e);
		}
	}


	public GoodDao getMain() {
		return main;
	}


	public void setMain(GoodDao main) {
		this.main = main;
	}

}
