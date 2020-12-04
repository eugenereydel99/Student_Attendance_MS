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
                name = entity.firstName,
                surname = entity.secondName,
                patronymic = entity.lastName,
                group = entity.group,
                email = entity.email
        )
    }

    override fun mapToEntity(domainModel: User): UserEntity {
        return UserEntity(
                id = domainModel.id,
                firstName = domainModel.name,
                secondName = domainModel.surname,
                lastName = domainModel.patronymic,
                group = domainModel.group,
                email = domainModel.email
        )
    }

}