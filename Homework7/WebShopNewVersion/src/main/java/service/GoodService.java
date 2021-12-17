package service;

import java.util.ArrayList;
import java.util.List;

import com.google.protobuf.ServiceException;

import dao.GoodDao;
import dao.MainDao;
import entity.Good;
import exception.DaoException;

public class GoodService implements MainService<Good> {
	private MainDao<Good> main = new GoodDao();
			
	@Override
	public List<Good> loadAll() throws ServiceException {
		List<Good> goods = new ArrayList<>();
		try {
			goods = main.getAll();
		} catch (DaoException e) {
			throw new ServiceException("Проблемы с загрузкой товаров из БД", e);
		}
		return goods;
	}

	@Override
	public List<Good> load(String name) throws ServiceException {
		List<Good> goods = new ArrayList<>();
		try {
			goods = main.find(name);
		} catch (DaoException e) {
			throw new ServiceException("Проблемы с загрузкой товаров из БД", e);
		}
		return goods;
	}

	@Override
	public boolean add(Good object) throws ServiceException {
		boolean isResult;
		try {
			main.setGoods(object);
			isResult =  true;
		} catch (DaoException e) {
			isResult =  false;
			throw new ServiceException("Проблемы с добавлением товара в БД", e);
		}
		return isResult;
	}

	@Override
	public boolean delete(String name) throws ServiceException {
		boolean isResult;
		try {
			main.delete(name);
			isResult =  true;
		} catch (DaoException e) {
			isResult =  false;
			throw new ServiceException("Проблемы с удалением товара в БД", e);
		}
		return isResult;
	}

	@Override
	public boolean update(String name, String nameNew, String priceNew) throws ServiceException {
		boolean isResult;
		try {
			main.update(name, nameNew, priceNew);
			isResult =  true;
		} catch (DaoException e) {
			isResult =  false;
			throw new ServiceException("Проблемы с изменением товара в БД", e);
		}
		return isResult;
	}

}
