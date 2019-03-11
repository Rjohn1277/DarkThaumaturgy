package Managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;

public class MyAssetManager {

    private static final String TAG = MyAssetManager.class.getSimpleName();

    private AssetManager assetManager;

    public MyAssetManager(AssetManager assetManager) {
        this.assetManager = assetManager;
    }

    public void unloadAsset(String assetFilePath) {
        if(assetManager.isLoaded(assetFilePath)) {
            assetManager.unload(assetFilePath);
        }
        else {
            Gdx.app.log(TAG, "Asset is not in memory" + assetFilePath);
        }
    }

    public float loadCompleted() {
        return assetManager.getProgress();
    }

    public int numberAssetsQueued() {
        return assetManager.getQueuedAssets();
    }

    public boolean updateAssetLoading() {
        return assetManager.update();
    }

    public boolean isAssetLoaded(String filename) {
        return assetManager.isLoaded(filename);
    }

    
}
