const imageInput = document.getElementById('imageFile');
const imagePreview = document.getElementById('imagePreview');

imageInput.addEventListener('change', () => {
	if (imageInput.files[0]) {
		let fileReader = new FileReader();
		fileReader.onload = () => {
			imagePreview.innerHTML = `<img src="${fileReader.result}" class="mb-3">`;
		}
		fileReader.readAsDataURL(imageInput.files[0]);
	} else {
		imagePreview.innerHTML = '';
	}
})

document.getElementById('imageFile').addEventListener('change', function(event) {
	const file = event.target.files[0];

	if (file) {
		const reader = new FileReader();

		// ファイルの読み込みが完了した時に呼ばれる
		reader.onload = function(e) {
			// 画像のプレビューを作成
			const imgElement = document.createElement('img');
			imgElement.src = e.target.result; // 読み込んだ画像を設定
			imgElement.classList.add('img-fluid', 'mt-3'); // クラスを追加してスタイリング

			const previewContainer = document.getElementById('imagePreview');
			previewContainer.innerHTML = ''; // 既存のプレビューがあれば削除
			previewContainer.appendChild(imgElement); // 新しい画像を表示
		};

		reader.readAsDataURL(file); // 画像ファイルをBase64形式で読み込む
	}
});
