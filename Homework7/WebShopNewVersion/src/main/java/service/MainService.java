package service;

import java.util.List;

import com.google.protobuf.ServiceException;

public interface MainService<T> {

	/**
	 * метод добавления
	 * @throws ServiceException 
	 */
	boolean add(T object) throws ServiceException;
	
	/**
	 * метод удаления
	 * @throws ServiceException 
	 */
	boolean delete(String name) throws ServiceException;
	
	/**
	 * метод удаления
	 * @throws ServiceException 
	 */
	boolean update(String name,String nameNew,String priceNew) throws ServiceException;
	
	/**
	 * метод получения всех товаров
	 * @throws ServiceException 
	 */
	List<T> loadAll() throws ServiceException;
	
	/**
	 * метод поиска товара по названию
	 * @throws ServiceException 
	 */
	List<T> load(String name) throws ServiceException;
}
