package com.example.student_attendance_ms.utils

import com.example.student_attendance_ms.database.EventEntity
import com.example.student_attendance_ms.database.UserEntity
import com.example.student_attendance_ms.network.model.Event
import com.example.student_attendance_ms.network.model.User
import javax.inject.Inject

/*
* Костыльное решение для преобразования сущностей одного типа в сущности другого
* (работает только в том случае, если аттрибуты этих типов совпадают по количеству)
* */

interface DataMapper<Entity, DomainModel> {

    fun mapFromEntity(entity: Entity): DomainModel

    fun mapToEntity(domainModel: DomainModel): Entity
}

class UserProfileMapper @Inject constructor() : DataMapper<UserEntity, User> {
    override fun mapFromEntity(entity: UserEntity): User {
        return User(
                id = entity.id,
                firstName = entity.firstName,
                secondName = entity.secondName,
                lastName = entity.lastName,
                createdAt = entity.createdAt,
                updatedAt = entity.updatedAt,
                email = entity.email
        )
    }

    override fun mapToEntity(domainModel: User): UserEntity {
        return UserEntity(
                id = domainModel.id,
                firstName = domainModel.firstName,
                secondName = domainModel.secondName,
                lastName = domainModel.lastName,
                createdAt = domainModel.createdAt,
                updatedAt = domainModel.updatedAt,
                email = domainModel.email
        )
    }

}

class EventMapper @Inject constructor() : DataMapper<EventEntity, Event> {
    override fun mapFromEntity(entity: EventEntity): Event {
        return Event(
                id = entity.id,
                title = entity.title,
                location = entity.location,
                timeStart = entity.timeStart,
                timeEnd = entity.timeEnd,
                creator = entity.creator,
                comments = entity.comments,
                date = entity.date
        )
    }

    override fun mapToEntity(domainModel: Event): EventEntity {
        return EventEntity(
                id = domainModel.id,
                title = domainModel.title,
                location = domainModel.location,
                timeStart = domainModel.timeStart,
                timeEnd = domainModel.timeEnd,
                creator = domainModel.creator,
                comments = domainModel.comments,
                date = domainModel.date
        )
    }

    fun mapFromEntityList(entities: List<EventEntity>): List<Event> =
            entities.map {
                mapFromEntity(it)
            }

}