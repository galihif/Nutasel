package com.giftech.terbit.ui.components.molecules

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MarkChatRead
import androidx.compose.material.icons.outlined.School
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.giftech.terbit.R
import com.giftech.terbit.ui.theme.light_CustomColor2
import com.giftech.terbit.ui.theme.light_onCustomColor2

@Composable
fun HeroColumn(
    modifier: Modifier = Modifier,
    title:String,
    description:String,
    imageRes:Int,
    imageHeight: Int? = null,
    textColor: Color = MaterialTheme.colorScheme.onSurface
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(imageRes),
            contentDescription = "",
            modifier = if (imageHeight != null) Modifier.height(imageHeight.dp) else Modifier,
            contentScale = ContentScale.FillHeight
        )
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(16.dp),
            textAlign = TextAlign.Center,
            color = textColor
        )
        Text(
            text = description.ifBlank { "" },
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
            color = textColor
        )
    }
}

@Composable
fun ProfInfoColumn() {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Text(
            text = "Tentang Saya",
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = "Saya adalah seorang ahli gizi yang berdedikasi dan kompeten, dengan pengetahuan dan keterampilan yang saya miliki, saya mampu memberikan panduan pola hidup dan nutrisi yang tepat untuk kamu.",
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = "Organisasi",
            style = MaterialTheme.typography.titleMedium
        )
        Column {
            OrganisasiListItem(
                name = "DPC PERSAGI Kota Serang",
                desc = "Divisi Ilmiah dan Publikasi (2023 - 2028)",
                image = R.drawable.persagi
            )
            OrganisasiListItem(
                name = "HMPS Diploma III Gizi",
                desc = "Divisi Minat dan Bakat (2019 - 2020)",
                image = R.drawable.hmps
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = light_CustomColor2,
                contentColor = light_onCustomColor2
            ),
            shape = RoundedCornerShape(100.dp),
            contentPadding = PaddingValues(10.dp)
        ) {
            Icon(imageVector = Icons.Outlined.MarkChatRead, contentDescription = "")
            Spacer(Modifier.width(8.dp))
            Text(text = "Hubungi Sekarang")
        }
    }
}

@Composable
fun ProfKredColumn(
    onKTRClick : () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Text(
            text = "Pendidikan",
            style = MaterialTheme.typography.titleMedium
        )
        Column {
            OrganisasiListItem(
                name = "Universitas Esa Unggul",
                desc = "Strata I Ilmu Gizi (2022 - Sekarang)",
                image = R.drawable.ueu
            )
            OrganisasiListItem(
                name = "Poltekkes Kemenkes Yogyakarta",
                desc = "Diploma III Gizi (2019 - 2022)",
                image = R.drawable.poltekes
            )
        }
        Text(
            text = "Nomor STR",
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = "11 09 5  1 23-4567576",
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = "Pengalaman",
            style = MaterialTheme.typography.titleMedium
        )
        Column {
            OrganisasiListItem(
                name = "Kementrian Koordinator PMK",
                desc = "Gizi Olahraga (Des 2022 - Jan 2023)",
                image = R.drawable.pmk
            )
            OrganisasiListItem(
                name = "RS PKU Muhammadiyah Yogyakarta",
                desc = "Gizi Institusi (Feb - Mar 2022)",
                image = R.drawable.pku
            )
            OrganisasiListItem(
                name = "RS PKU Muhammadiyah Yogyakarta",
                desc = "Gizi Klinik (Jan -  Feb 2022)",
                image = R.drawable.pku
            )
            OrganisasiListItem(
                name = "Puskesmas Tempel 1",
                desc = "Gizi Masyarakat (Nov 2021)",
                image = R.drawable.tempel
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = onKTRClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ),
            shape = RoundedCornerShape(100.dp),
            contentPadding = PaddingValues(10.dp),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Icon(imageVector = Icons.Outlined.School, contentDescription = "")
            Spacer(Modifier.width(8.dp))
            Text(text = "KTR Nutrisionis")
        }
    }
}