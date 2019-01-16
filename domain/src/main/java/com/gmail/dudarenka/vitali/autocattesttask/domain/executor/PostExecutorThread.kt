package com.gmail.dudarenka.vitali.autocattesttask.domain.executor

import io.reactivex.Scheduler

interface PostExecutorThread {
    fun getScheduler(): Scheduler
}