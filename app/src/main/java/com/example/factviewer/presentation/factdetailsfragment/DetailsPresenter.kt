package com.example.factviewer.presentation.factdetailsfragment

import android.provider.CalendarContract
import com.arellomobile.mvp.InjectViewState
import com.example.factviewer.MainApplication
import com.example.factviewer.data.repository.DBAnimalRepository
import com.example.factviewer.domain.interactors.DataAccessInteractor
import com.example.factviewer.presentation.base.BaseMvpPresenter
import com.example.factviewer.utils.events.Events
import kotlinx.coroutines.launch
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import javax.inject.Inject

/**
 * Презентер view отображения деталей статьи
 */
@InjectViewState
class DetailsPresenter : BaseMvpPresenter<DetailView>(){
    @Inject
    lateinit var dataAccessInteractor: DataAccessInteractor

    init {
        MainApplication.instance.getAppComponent()?.inject(this)

    }

    /**
     * Загрузка статьи
     * @param id - уникальный идентификатор статьи
     */
    fun loadDetail(id : String){
            dataAccessInteractor.getById(id)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onDetailsLoaded(event : Events.OnDetailsReceived){
        viewState.onShowDetails(event.id, event.details!!)

    }
    fun onStart(){
        EventBus.getDefault().register(this)
    }

    fun onStop(){
        EventBus.getDefault().unregister(this)
    }
}