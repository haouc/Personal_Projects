//
//  MyCarTableViewCell.m
//  CarCola
//
//  Created by Hao Zhou on 3/5/15.
//  Copyright (c) 2015 Hao Zhou. All rights reserved.
//

#import "MyCarTableViewCell.h"

@implementation MyCarTableViewCell

- (void)awakeFromNib {
    // Initialization code
}

- (void)setSelected:(BOOL)selected animated:(BOOL)animated {
    [super setSelected:selected animated:animated];

    // Configure the view for the selected state
}

-(void)saveToDefault:(NSInteger)rate {
    NSUserDefaults *defaults = [NSUserDefaults standardUserDefaults];
    NSInteger tagInt = [defaults integerForKey:[NSString stringWithFormat:@"%@%@%@",self.carModelInCell.text,self.carTrimInCell.text,self.carYearInCell.text]];
    NSString *tag = [NSString stringWithFormat:@"%li", (long)tagInt];
    
    NSLog(@"Setter: the cell identifer is %@", [NSString stringWithFormat:@"%@%@%@",self.carModelInCell.text,self.carTrimInCell.text,self.carYearInCell.text]);
    NSLog(@"Setter: the cell tag is %@", tag);
    
    [defaults setInteger:rate forKey:tag];
    [defaults synchronize];
}

-(NSInteger)getFromDefault{
    NSUserDefaults *defaults = [NSUserDefaults standardUserDefaults];
    
    NSInteger tagInt = [defaults integerForKey:[NSString stringWithFormat:@"%@%@%@", self.carModelInCell.text, self.carTrimInCell.text, self.carYearInCell.text]];
    NSString *tag = [NSString stringWithFormat:@"%li", (long)tagInt];
    
    NSLog(@"Getter: the cell identifer is %@", [NSString stringWithFormat:@"%@%@%@",self.carModelInCell.text,self.carTrimInCell.text,self.carYearInCell.text]);
    NSLog(@"Getter: the cell tag is %@", tag);
    
    NSInteger rate = [defaults integerForKey:tag];
    
    NSLog(@"Getter: the rate is %ld", (long)rate);
    
    return rate;
}

- (IBAction)rateButton:(id)sender {
    NSInteger rate = [self getFromDefault];
    if(rate == 5){
        self.rateStar.image = nil;
        [self saveToDefault:0];
    }else if(rate == 0){
        self.rateStar.image = [UIImage imageNamed:@"oneStar"];
        [self saveToDefault:1];

    }else if (rate == 1){
        self.rateStar.image = [UIImage imageNamed:@"twoStar"];
        [self saveToDefault:2];

    }else if(rate == 2){
        self.rateStar.image = [UIImage imageNamed:@"threeStar"];
        [self saveToDefault:3];

    }else if (rate == 3){
        self.rateStar.image = [UIImage imageNamed:@"fourStar"];
        [self saveToDefault:4];

    }else if (rate == 4){
        self.rateStar.image = [UIImage imageNamed:@"fiveStar"];
        [self saveToDefault:5];

    }
    
    self.tag = arc4random();
}

- (IBAction)goToGallery:(id)sender {
    //initialize the array which carries
    self.sentData = [[NSMutableArray alloc] initWithObjects: _carMakeInCell.text, _carModelInCell.text, _carTrimInCell.text, _carYearInCell.text, nil];
    NSUserDefaults *carPicked = [NSUserDefaults standardUserDefaults];
    [carPicked setObject:self.sentData forKey:@"toGallery"];
    [carPicked synchronize];
    
    

    NSLog(@"The sent car is %@%@%@%@ in datapack to send %@, first data is pack is %@, what in userdefault is %@", self.carMakeInCell.text, self.carModelInCell.text, self.carTrimInCell.text, self.carYearInCell.text, self.sentData, [self.sentData objectAtIndex:0], [carPicked objectForKey:@"toGallery"]);
}
@end
