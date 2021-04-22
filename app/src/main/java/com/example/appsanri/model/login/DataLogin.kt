package com.example.appsanri.model.login

import com.google.gson.annotations.SerializedName

class DataLogin(
	@SerializedName("nip_pengajar")
	var nipPengajar: String? = null,
	@SerializedName("email_pengajar")
	var emailPengajar: String? = null,
	@SerializedName("id_pengajar")
	var idPengajar: String? = null,
	@SerializedName("password_pengajar")
	var passwordPengajar: String? = null,
	@SerializedName("no_telp_pengajar")
	var noTelpPengajar: String? = null,
	@SerializedName("nama_pengajar")
	var namaPengajar: String? = null
)