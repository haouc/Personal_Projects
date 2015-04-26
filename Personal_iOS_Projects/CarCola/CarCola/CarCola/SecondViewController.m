//
//  SecondViewController.m
//  CarCola
//
//  Created by Hao Zhou on 3/1/15.
//  Copyright (c) 2015 Hao Zhou. All rights reserved.
//

#import "SecondViewController.h"

@interface SecondViewController ()

@end

@implementation SecondViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    [self.view bringSubviewToFront:_sameCodeButton];
    self.thisButton.hidden = YES;
    
    if ([_code isEqualToString: @"MPCS51030"]){
        UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"Congratulations!" message:@"You just discovered the secret. Please click the pretty little car here to start your wonderful journey!" delegate:self cancelButtonTitle:@"Okay" otherButtonTitles: nil];
        
        [alert show];
        
        self.thisButton.hidden = NO;
        
        NSUserDefaults *sound = [NSUserDefaults standardUserDefaults];
        NSString *playSound = [sound objectForKey:@"audio"];
        if ([playSound integerValue]){
            
            [self playSound:@"Magic"];
        }
        
        
    }else if ([_inputCode.text isEqualToString: @""]){
        UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"Welcome" message:@"Please Enter the Secret Code to Discover Something Interesting!" delegate:self cancelButtonTitle:@"Go Ahead Try" otherButtonTitles: nil];
        
        [alert show];
    }else {
        UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"Sorry!" message:@"You couldn't find the treasure. Don't give up, try again! Little tip: what is the course code? Capital matters and no space..." delegate:self cancelButtonTitle:@"Try Again" otherButtonTitles: nil];
        
        [alert show];

        NSUserDefaults *sound = [NSUserDefaults standardUserDefaults];
        NSString *playSound = [sound objectForKey:@"audio"];
        if ([playSound integerValue]){
        
            [self playSound:@"Failure"];
        }
    }
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (IBAction)codeButton:(id)sender {
    _code = self.inputCode.text;
    [self viewDidLoad];
}

- (IBAction)findButton:(id)sender {

}

- (void)playSound:(NSString*)path{
    
    self.soundPath = [[NSBundle mainBundle]pathForResource:path ofType:@"wav"];
    self.audio = [[AVAudioPlayer alloc] initWithContentsOfURL:[NSURL fileURLWithPath:self.soundPath] error:NULL];
    self.audio.meteringEnabled = YES;
    self.audio.delegate = self;
    [self.audio prepareToPlay];
    [self.audio play];
}

@end
