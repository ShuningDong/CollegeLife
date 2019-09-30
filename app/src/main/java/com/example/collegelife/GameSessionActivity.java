package com.example.collegelife;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

/**
 * Created by adamcchampion on 2017/08/19.
 */

public class GameSessionActivity extends SingleFragmentActivity {
    private GameSessionFragment mGameSessionFragment;

    protected Fragment createFragment() {
        recoverFragment();
        return mGameSessionFragment;
    }

    public void humanTakesATurn(int posX, int posY) {
        recoverFragment();
        mGameSessionFragment.humanTakesATurn(posX, posY);
    }

    private void recoverFragment() {
        if (mGameSessionFragment == null) {
            // the game is being played.
            FragmentManager fm = getSupportFragmentManager();
            if (fm.getFragments().size() == 0) {
                mGameSessionFragment = new GameSessionFragment();
            }
            else {
                for (Fragment fragment: fm.getFragments()) {
                    if (fragment instanceof GameSessionFragment) {
                        mGameSessionFragment = (GameSessionFragment) fragment;
                    }
                }
            }
        }
    }
}