package dao;

import java.util.List;

import exception.DaoException;

public interface MainDao<T> {

	/**
	 * метод получения всех товаров для формирования каталога товаров
	 * 
	 * @throws DaoException
	 */
	List<T> getAll() throws DaoException;

	/**
	 * метод поиска товара по названию
	 */
	List<T> find(String name) throws DaoException;

	/**
	 * метод добавления нового товара
	 */
	void setGoods(T addObject) throws DaoException;

	/**
	 * метод изменения существующего товара
	 */
	void update(String oldValue, String newValueOne, String newValueTwo) throws DaoException;

	/**
	 * метод удаления существующего товара
	 */
	void delete(String name) throws DaoException;

}
