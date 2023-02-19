package com.jolybell.jolybellunofficial.dialogs

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.Window
import android.widget.TableRow
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import com.jolybell.jolybellunofficial.R
import com.jolybell.jolybellunofficial.databinding.DialogTableMessageBinding
import com.jolybell.jolybellunofficial.сommon.utils.ImageUtils.Companion.setUrlImage
import com.jolybell.jolybellunofficial.сommon.utils.UnitUtils

class TableMessageDialog(
    context: Context,
    private val text: String,
    private val urlImage: String,
    private val modelTable: ModelTable,
    themeRes: Int = R.style.CustomDialogTheme,
    private val dismiss: Boolean = true,
): Dialog(context, themeRes) {

    companion object {
        fun getInstance(context: Context, textTitle: String, text: String, urlImage: String): TableMessageDialog{
            val rows = text.substringAfter("|").split("\n").let {
                it.minus(it[1])
            }
            val table = ModelTable(
                rows.map {
                    val cells = it.split("|")
                    ModelRow(cells.filter { it.isNotBlank() })
                }
            )
            return TableMessageDialog(context, textTitle, urlImage, table)
        }
    }

    private lateinit var binding: DialogTableMessageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        setCancelable(dismiss)

        window!!.decorView.setBackgroundResource(android.R.color.transparent)
        requestWindowFeature(Window.FEATURE_NO_TITLE)

        binding = DialogTableMessageBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.messageTableDialog.text = text
        binding.tableImageDialog.setUrlImage(urlImage)

        modelTable.rows.forEachIndexed { indexRow, row ->
            val tableRow = createTableRow(indexRow)
            row.cells.forEach { cell ->
                tableRow.addView(createTextView(cell, indexRow))
            }
            binding.table.addView(tableRow)
        }

        binding.close.setOnClickListener {
            dismiss()
        }
    }

    private fun createTableRow(indexRow: Int): TableRow{
        val tableRow = TableRow(context)
        tableRow.layoutParams = TableRow.LayoutParams(
            TableRow.LayoutParams.WRAP_CONTENT,
            TableRow.LayoutParams.WRAP_CONTENT
        )
        tableRow.minimumHeight = UnitUtils.dpToPx(45f, context).toInt()
        tableRow.gravity = Gravity.CENTER
        tableRow.setBackgroundColor(context.getColor(
            if ((indexRow + 1) % 2 == 0)
                R.color.table_row_background_1
            else
                R.color.table_row_background_2
        ))
        return tableRow
    }

    private fun createTextView(cell: String, indexRow: Int): TextView{
        val text = TextView(context)
        text.text = cell
        text.gravity = Gravity.CENTER
        text.typeface = ResourcesCompat.getFont(
            context, if (indexRow == 0) R.font.futurademic else R.font.futuralightc
        )
        text.setTextColor(context.getColor(R.color.dark))
        return text
    }

    data class ModelTable(
        val rows: List<ModelRow>,
    )

    data class ModelRow(
        val cells: List<String>,
    )
}