package com.example.mysql.activity


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mysql.adapter.AlunoAdapter
import com.example.mysql.api.EnderecoAPI
import com.example.mysql.api.RetrofitHelper
import com.example.mysql.databinding.ActivityListagemAlunoBinding
import com.example.mysql.model.Aluno
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListagemAlunoActivity : AppCompatActivity() {//Início class

    //Configuração do viewBinding
    private val binding by lazy {
        ActivityListagemAlunoBinding.inflate(layoutInflater)
    }

    //variável do Adapter
    private  lateinit var adapter: AlunoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {//Início fun onCreate
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupRecyclerView()
        loadAlunos()


        //Botão para ir par ao cadastro de alunos
        binding.btnCadastroAluno.setOnClickListener {
            val intent = Intent(this, CadastroAlunoActivity::class.java)
            //startActivity(intent)
            cadastroAlunoActivityResultLauncher.launch(intent)
        }

    }//Fim fun onCreate




    private val cadastroAlunoActivityResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                // Atualizar a lista de alunos após cadastro
                loadAlunos()
            }
        }



    private fun setupRecyclerView() {
        adapter = AlunoAdapter()
        binding.alunosRecyclerView.layoutManager = LinearLayoutManager (this)
        binding.alunosRecyclerView.adapter = adapter
    }


    private fun loadAlunos() {
        val retrofit = RetrofitHelper.getRetrofitInstance()
        val service = retrofit.create(EnderecoAPI::class.java)
        val call = service.getAlunos()

        call.enqueue(object : Callback<List<Aluno>> {
            override fun onResponse(call: Call<List<Aluno>>, response: Response<List<Aluno>>) {
                if (response.isSuccessful) {
                    response.body()?.let { alunos ->
                        adapter.setData(alunos)
                    }
                }
            }

            override fun onFailure(call: Call<List<Aluno>>, t: Throwable) {
                // Handle failure
            }
        })
    }



}//Fim class










