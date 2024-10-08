package com.example.hospitalsystem.presentation.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.hospitalsystem.theme.Primary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextInput(
    modifier: Modifier = Modifier,
    hint: String,
    keyboardType: KeyboardType,
    leadingIcon: ImageVector?,
    trailingIcon: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onValueChanged: (String) -> Unit,
    isError: Boolean = false,
    readOnly: Boolean = false,
    errorMessage: String
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    var text by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(4.dp)
    ) {
        OutlinedTextField(
            value = text,
            onValueChange = {
                text = it
                onValueChanged(it)
            },
            shape = RoundedCornerShape(16.dp),
            label = { Text(hint) },
            leadingIcon = {
                if (leadingIcon != null) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Icon(
                            imageVector = leadingIcon,
                            contentDescription = null,
                            tint = Primary
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        VerticalDivider(
                            modifier = Modifier
                                .height(24.dp)
                                .width(4.dp),
                            color = Primary
                        )
                    }
                }
            },
            readOnly = readOnly,
            trailingIcon = trailingIcon,
            visualTransformation = visualTransformation,
            isError = isError,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = keyboardType,
                imeAction = ImeAction.Done
            ),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Primary,
                unfocusedBorderColor = Color.Gray,
                cursorColor = Primary
            ),
            keyboardActions = KeyboardActions(onDone = {
                keyboardController?.hide()
            }),
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        if (isError) {
            Text(
                text = errorMessage,
                color = Color.Red,
                modifier = Modifier.padding(start = 16.dp, top = 4.dp)
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextInputCalls(
    modifier: Modifier = Modifier,
    hint: String,
    lines: Boolean,
    keyboardType: KeyboardType,
    trailingIcon: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onValueChanged: (String) -> Unit,
    isError: Boolean = false,
    errorMessage: String
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    var text by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(4.dp)
    ) {
        OutlinedTextField(
            value = text,
            onValueChange = {
                text = it
                onValueChanged(it)
            },
            shape = RoundedCornerShape(16.dp),
            label = { Text(hint) },
            trailingIcon = trailingIcon,
            visualTransformation = visualTransformation,
            isError = isError,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = keyboardType,
                imeAction = ImeAction.Done
            ),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Primary,
                unfocusedBorderColor = Color.Gray,
                cursorColor = Primary
            ),
            keyboardActions = KeyboardActions(onDone = {
                keyboardController?.hide()
            }),
            singleLine = lines,
            modifier = modifier.fillMaxWidth()
        )
        if (isError) {
            Text(
                text = errorMessage,
                color = Color.Red,
                modifier = Modifier.padding(start = 16.dp, top = 4.dp)
            )
        }
    }
}