package com.contentmgmtsystem.persistence

import org.jetbrains.exposed.sql.Table

object ProductTable : Table() {
    val upc = integer("upc").autoIncrement()
    val name = varchar("name", 50)
    val description = varchar("description", 100)

    override val primaryKey: PrimaryKey = PrimaryKey(upc)
}