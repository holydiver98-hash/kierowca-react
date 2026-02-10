package com.example.kierowca2.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contracts_ext")
data class ContractExtEntity(
    @PrimaryKey @ColumnInfo(name = "contract_id") val contractId: String,
    @ColumnInfo(name = "contract_conclusion_date") val contractConclusionDate: String?,
    @ColumnInfo(name = "contract_start_date") val contractStartDate: String?,
    @ColumnInfo(name = "contract_end_date") val contractEndDate: String?,
    @ColumnInfo(name = "contract_number") val contractNumber: String?,
    @ColumnInfo(name = "contract_short_name") val contractShortName: String?,
    @ColumnInfo(name = "contract_operators_name") val contractOperatorsName: String?,
    @ColumnInfo(name = "contract_desc") val contractDesc: String?,
    @ColumnInfo(name = "contract_op_id") val contractOpId: String?
)