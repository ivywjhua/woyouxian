
#properties = ['object oriented', 'duck typed', 'productive', 'fun']
#properties.each {|property | puts "Ruby is #{property}"}

puts "Pleae enter a guess number:"
target_num = rand(100)

guess_num = gets().to_i

until target_num == guess_num
	if target_num < guess_num
		puts "Your guess number is high"
	else
		puts "Your guess number is low"
	end
	guess_num = gets().to_i
end

puts "You are correct, the target num is #{guess_num}"
puts "The real target number is #{target_num}"


















