package com.gmail.dudarenka.vitali.autocattesttask.presentation.executor

import com.gmail.dudarenka.vitali.autocattesttask.domain.executor.PostExecutorThread
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers

class UIThread : PostExecutorThread {
    override fun getScheduler(): Scheduler {
        return AndroidSchedulers.mainThread()
    }
}