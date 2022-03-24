package com.example.mysubscribers.repository

import androidx.lifecycle.LiveData
import com.example.mysubscribers.data.db.dao.SubscriberDAO
import com.example.mysubscribers.data.db.entity.SubscriberEntity


class DatabaseDataSource(
    private val subscriberDAO:SubscriberDAO // NECESSÁRIO DAO, POIS ELE QUEM FAZ ACESSO AO DATABASE

) :SubscriberRepository{
    override suspend fun insertSubscriber(name: String, email: String): Long {
        val subscriber = SubscriberEntity(
            name = name,
            email = email
        )
        return subscriberDAO.insert(subscriber)
    }

    override suspend fun updateSubscriber(id: Long, name: String, email: String) {
        val subscriber = SubscriberEntity(
            id = id,
            name = name,
            email = email
        )
        return subscriberDAO.update(subscriber)
    }

    override suspend fun deteSubscriber(id: Long) {
        subscriberDAO.delete(id)
    }

    override suspend fun deleteAllSubscribers() {
        subscriberDAO.deleteAll()
    }

    override suspend fun getAllSubscribers(): LiveData<List<SubscriberEntity>> {
        return subscriberDAO.getAll()
    }
}