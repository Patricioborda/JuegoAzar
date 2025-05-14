package com.example.juegoazar.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.ui.text.TextStyle
import androidx.compose.material3.MaterialTheme

@Composable
fun GuessInput(
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    modifier: Modifier = Modifier,
    hint: String = "Adivina el número (1-5)",
    textStyle: TextStyle = MaterialTheme.typography.bodyLarge
) {
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .fillMaxWidth()
            .border(1.dp, Color.Gray)
            .padding(16.dp),
        singleLine = true,
        textStyle = textStyle,
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                if (value.text.isEmpty()) {
                    Text(hint, style = textStyle)
                }
                innerTextField()
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun GuessInputPreview() {
    GuessInput(value = TextFieldValue(""), onValueChange = {})
}
