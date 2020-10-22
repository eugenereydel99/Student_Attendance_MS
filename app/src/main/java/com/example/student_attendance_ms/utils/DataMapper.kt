package com.example.student_attendance_ms.utils

import com.example.student_attendance_ms.database.CurrentUser
import com.example.student_attendance_ms.network.model.User
import javax.inject.Inject

/*
* Костыльное решение для преобразования сущностей одного типа в сущности другого
* (работает только в том случае, если аттрибуты этих типов совпадают)
* */

interface DataMapper<Entity, DomainModel> {

    fun mapFromEntity(entity: Entity): DomainModel

    fun mapToEntity(domainModel: DomainModel): Entity
}

class UserProfileMapper @Inject constructor(): DataMapper<CurrentUser, User> {
    override fun mapFromEntity(entity: CurrentUser): User {
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

    override fun mapToEntity(domainModel: User): CurrentUser {
        return CurrentUser(
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
