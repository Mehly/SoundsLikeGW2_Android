package at.mehlox.guildwars.data.ormlite;

import java.sql.SQLException;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.OrmLiteDao;
import org.androidannotations.annotations.EBean.Scope;

import at.mehlox.guildwars.rest.entities.base.ItemBase;

import com.j256.ormlite.dao.Dao;

@EBean(scope = Scope.Singleton)
public class DatabaseHelper {

	@OrmLiteDao(helper = DatabaseInitializationHelper.class, model = ItemBase.class)
	Dao<ItemBase, Integer> mItemDao;

	public void createOrUpdateItem(ItemBase item) throws SQLException {
		mItemDao.createOrUpdate(item);
	}

	public ItemBase getItem(int itemId) throws SQLException {
		return mItemDao.queryForId(itemId);
	}

}
