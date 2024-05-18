package com.romka_po.assistent.ui.components.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.SupervisorAccount
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun AuthField(
    textValue: MutableState<String> = mutableStateOf(""),
    position: PositionInColumn = PositionInColumn.TOP,
    type: InputType = InputType.LOGIN
) {
    val shape = when (position) {
        PositionInColumn.TOP -> RoundedCornerShape(
            topStart = 40.dp,
            topEnd = 40.dp,
            bottomStart = 10.dp,
            bottomEnd = 10.dp
        )

        PositionInColumn.MIDDLE -> RoundedCornerShape(
            topStart = 10.dp,
            topEnd = 10.dp,
            bottomStart = 10.dp,
            bottomEnd = 10.dp
        )

        PositionInColumn.BOTTOM -> RoundedCornerShape(
            topStart = 10.dp,
            topEnd = 10.dp,
            bottomStart = 40.dp,
            bottomEnd = 40.dp
        )
    }

    val icon = when (type) {
        InputType.EMAIL -> Icons.Default.MailOutline
        InputType.LOGIN -> Icons.Outlined.SupervisorAccount
        InputType.PASSWORD -> Icons.Outlined.Lock
    }

    val text = when (type) {
        InputType.EMAIL -> "Электронная почта"
        InputType.LOGIN -> "Имя"
        InputType.PASSWORD -> "Пароль"
    }
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.onSecondary, shape = shape)
            .padding(24.dp),
        shape = RoundedCornerShape(8.dp),
        value = textValue.value,
        visualTransformation = if (type == InputType.PASSWORD) PasswordVisualTransformation() else VisualTransformation.None,
        keyboardOptions = if (type == InputType.PASSWORD) KeyboardOptions(keyboardType = KeyboardType.Password) else KeyboardOptions.Default,
        textStyle = MaterialTheme.typography.titleMedium,
        onValueChange = { textValue.value = it },
        colors = TextFieldDefaults.colors(
            unfocusedTextColor = Color.Unspecified,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent,
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent
        ),
        label = { Text(text, style = MaterialTheme.typography.bodyMedium) },
        trailingIcon = {
            Icon(imageVector = icon, contentDescription = null)
        }
    )
}