package com.example.student_attendance_ms.utils

import com.example.student_attendance_ms.database.UserEntity
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
                group = entity.group,
                email = entity.email
        )
    }

    override fun mapToEntity(domainModel: User): UserEntity {
        return UserEntity(
                id = domainModel.id,
                firstName = domainModel.firstName,
                secondName = domainModel.secondName,
                lastName = domainModel.lastName,
                group = domainModel.group,
                email = domainModel.email
        )
    }

}

//class EventMapper @Inject constructor() : DataMapper<EventEntity, Event> {
//    override fun mapFromEntity(entity: EventEntity): Event {
//        return Event(
//                id = entity.id,
//                title = entity.title,
//                eventType = entity.eventType,
//                location = entity.location,
//                timeStart = entity.timeStart,
//                timeEnd = entity.timeEnd,
//                creator = entity.creator,
//                comments = entity.comments,
//                date = entity.date,
//                checkType = entity.checkType
//        )
//    }
//
//    override fun mapToEntity(domainModel: Event): EventEntity {
//        return EventEntity(
//                id = domainModel.id,
//                title = domainModel.title,
//                eventType = domainModel.eventType,
//                location = domainModel.location,
//                timeStart = domainModel.timeStart,
//                timeEnd = domainModel.timeEnd,
//                creator = domainModel.creator,
//                comments = domainModel.comments,
//                date = domainModel.date,
//                checkType = domainModel.checkType
//        )
//    }
//
//    fun mapFromEntityList(entities: List<EventEntity>): List<Event> =
//            entities.map {
//                mapFromEntity(it)
//            }
//
//}