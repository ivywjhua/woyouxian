
num_arr = [78, 36, 55, 32, 893, 23, 30, 8923, 50, 934, 84, 50, 2390, 482, 902, 383]
puts "Number array length is: #{num_arr.length}"

puts "Print array four numbers at a time, use each and mod"
i = 0
num_arr.each do |number|
	p num_arr[i,4] if(i%4 == 0)
	i = i + 1
end

puts "Print array four numbers at a time, use each_slice"
num_arr.each_slice(4) {|slice| p slice}


puts "Print array four numbers at a time........"
num_arr = (1..16).to_a

num_arr.each do |number|
	p num_arr[((number - 4)..number)] if number % 4 == 0
	
end


















