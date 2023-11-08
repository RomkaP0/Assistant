package com.romka_po.assistent.ui.components.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.romka_po.assistent.R

private const val success = "Successfully"
private const val verify = "Verify email"
private const val verifyHint = "Please go to the link that sent to yourmail@example.com"
private const val successHint = "Yahoo! You have successfully verified \n" +
        "the account."
private const val successButtonText = "Done"
private const val verifyButtonText = "Open email"

@Preview(showBackground = true)
@Composable
fun BottomAuthDialog(isSent: Boolean = true) {
    val label = if (isSent) verify else success
    val hint = if (isSent) verifyHint else successHint
    val buttonText = if (isSent) verifyButtonText else successButtonText
    val image = if (isSent) R.drawable.auth_wait else R.drawable.auth_done

    Dialog(
        onDismissRequest = { /*TODO*/ },
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface(
            Modifier
                .padding(horizontal = 8.dp)
                .clip(RoundedCornerShape(32.dp)), tonalElevation = 8.dp, shadowElevation = 8.dp
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .height(520.dp)
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                FilledIconButton(
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(8.dp)
                        .size(40.dp),
                    onClick = { /*TODO*/ },
                    colors = IconButtonDefaults.filledIconButtonColors(
                        containerColor = IconButtonDefaults.filledIconButtonColors().containerColor.copy(
                            alpha = 0.1f
                        )
                    )
                ) {
                    Icon(imageVector = Icons.Default.Close, contentDescription = null)
                }

                Image(
                    modifier = Modifier.size(152.dp),
                    painter = painterResource(id = image),
                    contentDescription = null
                )

                Text(
                    text = label, style = TextStyle(
                        fontSize = 32.sp,
                        lineHeight = 40.sp,
                        fontWeight = FontWeight(700),
                        color = Color(0xFFFFFFFF),

                        textAlign = TextAlign.Center,
                    )
                )
                Text(
                    text = hint, textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontSize = 14.sp,
                        lineHeight = 24.sp,
                        fontWeight = FontWeight(500),
                        color = Color(0x80FFFFFF),

                        textAlign = TextAlign.Center,
                    )
                )
                Spacer(modifier = Modifier)
                FilledTonalButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { /*TODO*/ },
                    contentPadding = PaddingValues(16.dp)
                ) {
                    Text(
                        text = buttonText, style = TextStyle(
                            fontSize = 16.sp,
                            lineHeight = 24.sp,
                            fontWeight = FontWeight(700),
                            color = Color(0xFFFFFFFF),

                            )
                    )
                }
            }
        }
    }
}