window.onload=function(){
	const youtube = 'https://youtu.be/';//유튜브
	let videoArray = [];
	let oembeds = document.getElementsByTagName('oembed');
	for(let i=0;i<oembeds.length;i++){
		let url = oembeds[i].getAttribute('url');
		if(url.includes(youtube)){
			var output = '';
			output += '<div style="position: relative; padding-bottom: 100%;max-height:400px; padding-bottom:28%;width:700px;margin:0 auto;">';
			output += '<iframe src="https://www.youtube.com/embed/'+url.substring(youtube.length)+'"';
			output += 'style="position: absolute; width: 100%; height: 400px;max-height: 400px; top: 0; left: 0;"';
			output += 'frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen>';
			output += '</iframe>';
			output += '</div>';
			videoArray.push(output);
		} 
	}  
	if(videoArray.length>0){
		let media = document.getElementsByClassName('media');
		for(let i=0;i<media.length;i++){
			media[i].innerHTML=videoArray[i];
		}
	}
};