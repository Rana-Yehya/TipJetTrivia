import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Slider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import com.example.tipjettrivia.components.InputField

import com.example.tipjettrivia.widgets.RoundButton

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun BillEntry(  modifier: Modifier = Modifier,
                splitState: MutableState<Int>,
                tipAmounntState: MutableState<Double>,
                tipPerPersonState: MutableState<Double>,
                onValueChange : (String) -> Unit = {}){
    val totalBillMoneyState = remember {
        mutableStateOf("")
    }
    // to check totalbillmoney is valid
    val checkState = remember (totalBillMoneyState.value) {
        totalBillMoneyState.value.trim().isNotEmpty()
    }

    val percentageChangeState = remember {
        mutableStateOf(0f)
    }
    val tipPercentageState = (percentageChangeState.value * 100).toInt()

    // To dismiss the keyboard after finishing
    val KeyboardController = LocalSoftwareKeyboardController.current

    Surface(modifier = modifier
        .padding(12.dp)
        .height(500.dp)
        .fillMaxWidth(),
        shape = RoundedCornerShape(corner = CornerSize(8.dp)),
        border = BorderStroke(width = 1.dp, color = Color.LightGray)
    ) {
        Column() {

            InputField(
                valueState = totalBillMoneyState,
                labelId = "Enter Bill",
                enabled = true,
                isSingleLine = true,
                onAction = KeyboardActions {
                    if (!checkState) return@KeyboardActions
                    onValueChange(totalBillMoneyState.value.trim())

                    KeyboardController?.hide()
                })
            if (checkState) {
                // Split
                Row(
                    modifier = modifier.padding(horizontal = 5.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {


                    Text(
                        text = "Split", modifier = modifier
                            .padding(start = 5.dp)
                            .align(alignment = Alignment.CenterVertically)
                    )
                    Spacer(modifier = modifier.width(150.dp))
                    // Buttons
                    Row(
                        modifier = modifier.padding(horizontal = 5.dp),
                        horizontalArrangement = Arrangement.End
                    ) {


                        RoundButton(imageVector = Icons.Default.Remove, onClick = {
                            if (splitState.value != 1) splitState.value = splitState.value - 1
                            tipPerPersonState.value = calculateTipPerPerson(
                                totalBillMoney = totalBillMoneyState.value.toDouble(),
                                splitBy = splitState.value,
                                tipPercentage = tipPercentageState
                            )
                        })
                        Text(
                            text = "${splitState.value}",
                            modifier = modifier
                                .align(Alignment.CenterVertically)
                                .padding(start = 5.dp, end = 5.dp)
                        )
                        RoundButton(imageVector = Icons.Default.Add, onClick = {
                            splitState.value = splitState.value + 1
                            tipPerPersonState.value = calculateTipPerPerson(
                                totalBillMoney = totalBillMoneyState.value.toDouble(),
                                splitBy = splitState.value,
                                tipPercentage = tipPercentageState
                            )
                        })


                    }

                }
                // Tip
                Row(
                    modifier = modifier.padding(horizontal = 5.dp, vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Tip", modifier = modifier
                            .padding(5.dp)
                            .align(Alignment.CenterVertically)
                    )
                    Spacer(modifier = modifier.width(180.dp))
                    Text(
                        "$ ${tipAmounntState.value}", modifier = modifier
                            .padding(5.dp)
                            .align(Alignment.CenterVertically)
                    )
                }
                // Slider
                Column(
                    modifier = modifier.padding(5.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "$tipPercentageState %", modifier = modifier.padding(5.dp))
                    Spacer(modifier = modifier.height(10.dp))
                    Slider(value = percentageChangeState.value, onValueChange = {
                        percentageChangeState.value = it
                        tipAmounntState.value = calculateTipAmount(
                            totalBillMoneyState.value.toDouble(),
                            tipPercentageState
                        )
                        tipPerPersonState.value = calculateTipPerPerson(
                            totalBillMoney = totalBillMoneyState.value.toDouble(),
                            splitBy = splitState.value,
                            tipPercentage = tipPercentageState
                        )
                    }, steps = 10, modifier = modifier.padding(start = 16.dp, end = 16.dp))
                }
            }

            else Box {}
        }
    }
}
