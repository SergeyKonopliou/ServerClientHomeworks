package by.main;

import java.util.ArrayList;
import java.util.List;

public class GoodsCatalog {
	private List<Good> goods;

	public GoodsCatalog() {
		goods = new ArrayList<>();
		Good good = new Good();
		good.setName("One");
		good.setPrice(100);
		Good good2 = new Good();
		good2.setName("Two");
		good2.setPrice(120);
		Good good3 = new Good();
		good3.setName("Three");
		good3.setPrice(80);
		goods.add(good);
		goods.add(good2);
		goods.add(good3);
	}

	public List<Good> findGoods(String name) {
		List<Good> foundGoods = new ArrayList<>();
		for (Good good : goods) {
			if (good.getName().equalsIgnoreCase(name)) {
				foundGoods.add(good);
			}
		}
		return foundGoods;
	}

	public List<Good> getGoods() {
		return goods;
	}

	public void setGoods(List<Good> goods) {
		this.goods = goods;
	}
}
